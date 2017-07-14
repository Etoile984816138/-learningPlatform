<template>
    <div id="app">
        <v-header></v-header>
        <v-rightSider></v-rightSider>
        <mu-float-button icon="chevron_right" class="left-list-btn" label="toggle drawer" @click="toggle()" />
        <mu-drawer :open="open" :docked="docked" @close="toggle()">
            <mu-list @itemClick="docked ? '' : toggle()" class="chapter-list">
                <mu-list-item v-for="chapter in courseData.chapters" :title="chapter.title" disabled :key="chapter.id">
                    <template v-for="lesson in chapter.lessons">
                        <template v-if="lesson.material.type == 0">
                            <mu-list-item :title="lesson.title" :key="lesson.id" :to="'/video/'+lesson.id+'?cid='+cid">
                                <mu-icon value="live_tv" slot="left" />
                            </mu-list-item>
                        </template>
                        <template v-else-if="lesson.material.type == 1">
                            <mu-list-item :title="lesson.title" :to="'/audio/'+lesson.id+'?cid='+cid">
                                <mu-icon value="settings_voice" slot="left" />
                            </mu-list-item>
                        </template>
                        <template v-else>
                            <mu-list-item :title="lesson.title" :to="'/page/'+lesson.id+'?cid='+cid">
                                <mu-icon value="filter_none" slot="left" />
                            </mu-list-item>
                        </template>
                    </template>
                </mu-list-item>
                <!-- <mu-list-item title="Menu Item 2" />
                <mu-list-item title="Menu Item 3" /> -->
            </mu-list>
            <mu-icon-button icon="clear" v-if="docked" @click.native="open = false" title="Close" class="close-sider" />
        </mu-drawer>
        <div id="content" class="content">
            <mu-row gutter>
                <mu-col width="100" tablet="70" desktop="70">
                    <div class="player-content">
                        <transition name="slide-fade">
                            <router-view :lessonData="lessonData" :bus="bus" :discussType="discussType" :autoContinue="autoContinue" :nextId="next_id"></router-view>
                        </transition>
                        <!-- <v-video></v-video> -->
                        <!-- <v-page></v-page> -->
                        <!-- <v-audio></v-audio> -->
                    </div>
                    <div class="player-function" @mouseleave="setOutLineHide">
                        <mu-checkbox label="自动续播" class="demo-checkbox" v-if="lessonData.type != 2" v-model="autoContinue" />
                        <mu-flat-button label="下一课时" class="next-btn" primary v-else @click="toNext" />
                        <div>
                            <span @click="setPoint(1)">划重点</span>
                            <span @click="setPoint(2)">有疑问</span>
                            <span @mouseover="setOutLineShow">离线观看</span>
                        </div>
                    </div>
                    <div class="player-comment" v-show="playerFunctionShow">
                        <mu-text-field hintText="多行文本输入，默认 3行，最大6行" multiLine :rows="3" :rowsMax="6" fullWidth v-model="playerCommentConetnt" />
                        <mu-radio label="公开" name="group" nativeValue="0" v-model="playerComment" class="demo-radio" />
                        <mu-radio label="好友可见" name="group" nativeValue="1" v-model="playerComment" class="demo-radio" />
                        <mu-radio label="私密" name="group" nativeValue="2" v-model="playerComment" class="demo-radio" />
                        <mu-raised-button label="提交" class="send-player-comment" primary @click="submitPoint()" />
                    </div>
                </mu-col>
                <mu-col width="100" tablet="30" desktop="30">
                    <div class="comment-list-wrapper">
                        <mu-tabs :value="activeTab" @change="handleTabChange">
                            <mu-tab value="1" title="划重点" />
                            <mu-tab value="2" title="有疑问" />
                        </mu-tabs>
                        <div class="comment-list">
                            <mu-list>
                                <div class="clearfix" v-for="item in discussData">
                                    <mu-list-item :title="item.employee.name">
                                        <mu-avatar slot="left" :src="item.employee.port" />
                                        <p style="float:right" class="time">{{item.point_time}}</p>
                                    </mu-list-item>
                                    <p class="comment-content">{{item.content}}</p>
                                    <mu-icon-button icon="comment" class="add-comment-btn" @click="setComment('person_name')" />
                                </div>
                            </mu-list>
                        </div>
                        <div class="send-wrapper">
                            <mu-text-field hintText="" multiLine :rows="1" :rowsMax="6" v-model="comment" />
                            <mu-icon-button icon="send" class="send-comment-btn" />
                            <!-- <mu-raised-button label="发送" class="send-comment-btn" primary style=""/> -->
                        </div>
                    </div>
                    <div class="offLine">
                        <img src="http://s.stu.126.net/res/images/qrcode/nav_qrcode.png?06fd49e6d75c94fb2fa07f445c3b9571" v-show="outLineShow">
                    </div>
                </mu-col>
            </mu-row>
        </div>
        <v-footer></v-footer>
    </div>
</template>
<script>
import header from 'components/common/header/header.vue'
import footer from 'components/common/footer/footer.vue'
import rightSider from 'components/common/rightSider/rightSider.vue'

import video from 'entry/player/video.vue'
import audio from 'entry/player/audio.vue'
import page from 'entry/player/page.vue'

import Vue from 'vue'

export default {
    data() {
            return {
                open: false,
                docked: true,
                activeTab: '1',
                playerComment: '0',
                playerFunctionShow: false,
                courseData: {},
                lessonData: {},
                id: 5,
                comment: '',
                toComment: '',
                bus: new Vue(),
                playerCommentConetnt: '',
                autoContinue: false,
                discussData: [],
                discussType: 1,
                point_time: 0,
                cover: null,
                next_id: 0,
                outLineShow: false,
                type:1
            }
        },
        created() {
            this.getDiscussionByPoint(0)
            const _self = this;
            if (this.$route.params.id) {
                console.log('0000000')
                this.id = this.$route.params.id
            }else {
                this.id = 0;
            }

            this.$http.get('/lesson/' + this.id).then((response) => {
                response = response.body
                if (response.failure.length === 0) {
                    // 如果是PPT，要再次请求资源
                    console.log(response.success.type)
                    _self.lessonData = response.success
                    _self.next_id = response.next
                    if (response.success.type == 2) {
                        _self.$http.get('/lesson/pdf/' + _self.lessonData.url).then((response2) => {
                            response2 = response2.body
                             _self.lessonData.url = response2;
                        })
                    }
                } else {
                    alert(response.failure[0])
                }

            })

            this.$http.get('/course/' + this.cid + '/section').then((response) => {
                response = response.body
                    // console.log(response)
                if (response.failure.length === 0) {
                    this.courseData = response.success
                } else {
                    alert(response.failure[0])
                }

            })

            if (this.bus) {
                const _self = this;
                this.bus.$on("getDiscuss", function(msg) {
                    console.log("接收到其他组件传递的数据：");
                    _self.discussData = msg.discuss;
                    _self.point_time = msg.point_time;
                    console.log(_self.discussData)

                });
            };

        },
        methods: {
            handleTabChange(val) {
                const _self = this
                this.activeTab = val
                this.discussType = parseInt(val)
                this.getDiscussionByPoint(_self.point_time)
            },
            handleActive() {
                window.alert('tab active')
            },
            toggle(flag) {
                this.open = !this.open
                this.docked = !flag
            },
            setPoint(val) {
                this.type = val
                if (!this.playerFunctionShow) {
                    this.playerFunctionShow = true;
                } else {
                    this.playerFunctionShow = false;
                }

            },
            submitPoint() {
                let point_time = 0;
                // 视频
                if (this.lessonData.type == 0) {
                    point_time = document.getElementsByClassName('dplayer-ptime')[0].innerHTML;
                    let element = document.getElementsByClassName('dplayer-video-current')[0];
                    const canvas = document.createElement("canvas");
                    canvas.width = 700;
                    canvas.height = 500;
                    canvas.getContext('2d').drawImage(element, 0, 0, canvas.width, canvas.height);
                    this.cover = canvas;
                    // 音频
                } else if (this.lessonData.type == 1) {
                    point_time = document.getElementsByClassName('aplayer-ptime')[0].innerHTML;
                    // PPT
                } else {
                    point_time = document.getElementsByClassName('pdf_current_page')[0].value;
                    let page = document.getElementById('page').getElementsByTagName('canvas')[0];
                    console.log(page);
                    this.cover = page;
                }
                const _self = this;
                const params = {
                    type: _self.type,
                    flag: parseInt(_self.playerComment),
                    content: _self.playerCommentConetnt,
                    lesson_id: parseInt(_self.id),
                    point_time: point_time,
                    cover: _self.cover
                }
                console.log('------------')
                console.log(params)
                this.$http.post('/discuss/note', {
                    params: params
                }).then((response) => {
                    response = response.body
                        // console.log(response)
                    if (response.failure.length === 0) {
                        console.log(response)
                        this.playerFunctionShow = false;
                        alert('发送成功')
                            // this.lessonData = response.success
                    } else {
                        alert(response.failure[0])
                    }

                })


            },
            handleUrl() {
                var url = window.location.hash; //获取url中"?"符后的字串
                var theRequest = new Object();
                if (url.indexOf("?") != -1) {
                    var str = url.substr((url.indexOf("?") + 1));
                    var strs = str.split("&");
                    for (var i = 0; i < strs.length; i++) {
                        theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
                    }
                }
                return theRequest;
            },
            getLesson(id) {
                this.$http.get('/lesson/' + id).then((response) => {
                    response = response.body
                        // console.log(response)
                    if (response.failure.length === 0) {
                        console.log(response)
                        this.lessonData = response.success
                    } else {
                        alert(response.failure[0])
                    }

                })
            },
            setComment(person_name) {
                this.comment = '回复@' + person_name + '：';
                this.toComment = person_name;

            },
            getDiscussionByPoint(point_time) {
                const _self = this;
                this.$http.get('/discuss/' + this.$route.params.id + '/lesson/' + this.discussType, {
                    params: {
                        point_time: point_time
                    }
                }).then((response) => {
                    response = response.body
                    if (response.failure.length === 0) {
                        console.log(response)
                        _self.discussData = response.success
                    } else {
                        alert(response.failure[0])
                    }

                })
            },
            setOutLineShow() {
                this.outLineShow = true;
            },
            setOutLineHide() {
                this.outLineShow = false;
            },
            toNext() {
                const _self = this;
                _self.$http.get('/lesson/' + _self.next_id).then((response) => {
                    response = response.body
                        // console.log(response)
                    if (response.failure.length === 0) {
                        let type = response.success.type
                        if (type === 0) {
                            window.location = '#/video/' + _self.next_id+'?cid='+this.cid
                        } else if (type === 1) {
                            window.location = '#/audio/' + _self.next_id+'?cid='+this.cid
                        } else {
                            window.location = '#/page/' + _self.next_id+'?cid='+this.cid
                        }
                    } else {
                        alert(response.failure[0])
                    }

                })
            }
        },
        components: {
            'v-header': header,
            'v-footer': footer,
            'v-rightSider': rightSider,
            'v-video': video,
            'v-page': page,
            'v-audio': audio
        },
        computed: {
            setId: function() {


            },
            cid: function() {
                // alert(this.handleUrl()['cid'])
                if (typeof this.handleUrl()['cid'] == 'undefined') {
                    return 0;
                } else {
                    return this.handleUrl()['cid']
                }
            }
        }
}
</script>
<style lang="less">
@main-color: #009688;
.content {
    padding: 50px 100px;
    margin-top: 50px;
}

.content-title p {
    margin: 40px 0 20px 0;
    padding: 5px;
    border-left: 5px solid @main-color;
    font-size: 16px;
}

.mu-list {
    overflow: hidden;
}

.left-list-btn {
    position: fixed;
    top: 40%;
    left: -20px;
}

.player-content {
    width: 100%;
    height: 500px;
    border: 1px solid #ccc;
}

.player-function {
    display: flex;
    justify-content: space-between;
    margin-top: 20px;
    .mu-checkbox {
        // margin-top: 20px;
    }
    span {
        vertical-align: center;
        margin: 0;
        padding: 0;
        cursor: pointer;
        &:hover {
            color: @main-color;
        }
    }
}

.send-player-comment {
    float: right;
}

.comment-list-wrapper {
    position: relative;
    z-index: 1;
    height: 500px;
    border: 1px solid #ccc;
    .mu-item-content {
        display: flex;
        .time {
            color: #ccc;
            white-space: nowrap;
            margin: 0;
            padding: 0;
        }
    }
    .comment-content {
        padding: 0 20px;
    }
    .send-wrapper {
        position: absolute;
        bottom: 0;
        padding: 0 10px;
        // height: 40px;
        .mu-text-field {
            width: 230px;
        }
        .send-comment-btn {
            float: right;
            position: relative;
            top: -10px;
            color: @main-color;
        }
    }
    .comment-list {
        margin-top: 30px;
        margin-bottom: 40px;
        max-height: 350px;
        overflow: auto;
    }
    .add-comment-btn {
        float: right;
        color: @main-color;
    }
}

.clearfix:after {
    content: ".";
    display: block;
    height: 0;
    clear: both;
    visibility: hidden
}

.close-sider {
    display: block;
    // justify-content:center;
    position: fixed;
    bottom: 10px;
    left: 40%;
    margin: 0 auto;
    background: @main-color;
    color: #fff;
    box-shadow: 2px 2px 5px #b2b2b2;
}

.slide-fade-enter-active {
    transition: all .5s ease;
}

.slide-fade-leave-active {
    /* transition: all .5s cubic-bezier(1.0, 0.5, 0.8, 1.0);*/
}

.slide-fade-enter,
.slide-fade-leave-active {
    transform: translateX(10px);
    opacity: 0;
}

.router-link-exact-active {
    .mu-icon,
    .mu-item-title {
        color: @main-color;
    }
}

.offLine {
    position: relative;
    display: flex;
    justify-content: flex-start;
    width: 100%;
    height: 140px;
}
</style>
