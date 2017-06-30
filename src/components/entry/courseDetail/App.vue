<template>
    <div id="app">
        <v-header></v-header>
        <v-rightSider></v-rightSider>
        <div id="content" class="content">
            <v-courseHeader :courseData="courseData" :bus="bus"></v-courseHeader>
            <mu-row gutter>
                <mu-col width="100" tablet="70" desktop="70" class="tab-detail">
                    <mu-row>
                        <mu-col width="100" tablet="50" desktop="50">
                            <div class="tab">
                                <mu-tabs :value="activeTab" @change="handleTabChange">
                                    <mu-tab value="introduction" title="介绍" />
                                    <mu-tab value="chapter" title="章节" />
                                    <mu-tab value="discussion" title="讨论区" />
                                </mu-tabs>
                            </div>
                        </mu-col>
                    </mu-row>
                    <div>
                        <!-- 课程介绍 -->
                        <p class="introduction tab-page" :class="{active: activeTab === 'introduction'}">{{courseData.description}}</p>
                        <!-- 课程章节 -->
                        <div class="chapter tab-page" :class="{active: activeTab === 'chapter'}">
                            <mu-list>
                                <mu-list-item v-for="(section, sectionIndex) in chapter.sections" :title="'第'+(sectionIndex+1)+'章：'+section.name" :key="section.id" toggleNested>
                                    <mu-list-item v-for="(chapter,chapterIndex) in section.chapter" :title="'第'+(chapterIndex+1)+'课时：'+chapter.name" :key="chapter.id" slot="nested">
                                        <mu-list-item slot="right" class="chapter-states" disabled>
                                            <span :class="{active: chapter.noted}">划重点</span>
                                            <span :class="{active: chapter.question}">有疑问</span>
                                            <span :class="{active: chapter.complete}">已完成</span>
                                            <span>{{chapter.time}}</span>
                                        </mu-list-item>
                                    </mu-list-item>
                                </mu-list-item>
                            </mu-list>
                        </div>
                        <!-- 讨论区 -->
                        <div class="chapter tab-page" :class="{active: activeTab === 'discussion'}">
                            <mu-sub-header>欢迎进入课程讨论区，你可以与本课程的老师和同学在这里交流。如果你有课程相关的问题，请发到老师答疑区；经验、思考、创意、作品、转帖请发到综合讨论区。欢迎分享，鼓励原创，杜绝广告，请大家共同维护一个包容、积极、相互支持的交流氛围，谢谢。
                            </mu-sub-header>
                            <mu-raised-button label="发起主题" class="add-discussion" secondary @click="open" />
                            <mu-dialog :open="dialog" title="发起主题" @close="close">
                                <mu-select-field v-model="addDiscuss.type" label="*所选讨论区模块" @change="test">
                                    <mu-menu-item value="1" title="老师答疑区" />
                                    <mu-menu-item value="2" title="综合讨论区" />
                                </mu-select-field>
                                <div></div>
                                <mu-select-field v-model="s_index" label="选择章节" hintText="请选择章节" @input="test">
                                    <mu-menu-item v-for="(course,index) in chapter.sections" :key="course.id" :value="index" :title="course.name">
                                    </mu-menu-item>
                                </mu-select-field>
                                <mu-select-field v-model="addDiscuss.information_id" label="选择课时" hintText="请选择课时" @input="test" v-if="s_index">
                                    <mu-menu-item v-for="chapters in chapter.sections[s_index].chapter" :key="chapters.id" :value="chapters.id" :title="chapters.name">
                                    </mu-menu-item>
                                </mu-select-field>
                                <mu-text-field hintText="请输入标题" fullWidth label="标题" v-model="addDiscuss.title" />
                                <mu-text-field hintText="请输入内容" fullWidth label="主题内容" v-model="addDiscuss.content" multiLine/>
                                <mu-switch label="匿名" class="demo-switch" v-model="addDiscuss.isNick" />
                                <mu-flat-button slot="actions" @click="dialogClose" primary label="取消" />
                                <mu-raised-button slot="actions" primary @click="addDiscussF" label="确定" />
                            </mu-dialog>
                            <mu-list>
                                <mu-list-item disabled>
                                    <mu-list-item class="sub-list-title" @click="setDiscussionTitle('老师答疑区',1)">老师答疑区</mu-list-item>
                                    <mu-list-item class="sub-list-title" @click="setDiscussionTitle('综合讨论区',2)">综合讨论区</mu-list-item>
                                    </mu-list-item》>
                            </mu-list>
                            <mu-sub-header>{{discussionAreaTitle}}</mu-sub-header>
                            <mu-list>
                                <mu-list-item :title="discu.employee.name" disabled v-for="(discu,index) in discusionData" :key="discu.id">
                                    <mu-avatar slot="left" :src="discu.employee.port" />
                                    <span slot="describe">
                                        <!-- <mu-badge content="教师" secondary slot="right" v-show="discu.isTeacher"/> -->
                                        <span>{{discu.time}}</span>
                                    </span>
                                    <p @click="setClose(discu, index) " class="hover-state">{{discu.title}}</p>
                                    <transition name="slide-fade">
                                        <mu-list-item v-show="discu.close">{{discu.content}}</mu-list-item>
                                    </transition>
                                    <span style="float:right">
                                            <span>浏览 （{{discu.view}}）   </span>
                                    <span @click="setReply(discu,index)" class="hover-state">回复（{{discu.commends.length}}）  </span>
                                    <span class="hover-state" @click="setSupport(index)">点赞（{{discu.grate}}）</span>
                                    </span>
                                    <transition name="slide-fade">
                                        <div v-show="discu.active">
                                            <mu-list>
                                                <mu-list-item v-for="(reply,replyIndex) in discu.commends" :key="reply.from.id" :title="reply.from.name">
                                                    <mu-avatar slot="left" :src="reply.from.port" />
                                                    <span slot="describe">
                                                        <mu-badge content="教师" secondary slot="right" v-show="reply.from.isTeacher"/>
                                                        <span>{{reply.time}}</span>
                                                    </span>
                                                    <p>{{reply.content}}</p>
                                                    <span style="float:right">
                                                    <span class="hover-state" @click="setSubReply(reply)">回复  </span>
                                                    <span class="hover-state" @click="setReplySupport(index,replyIndex)">点赞（{{reply.grate}}）</span>
                                                    </span>
                                                </mu-list-item>
                                            </mu-list>
                                            <mu-text-field hintText="请输入文字" label="回复" v-model="replyText" fullWidth labelFloat/>
                                            <mu-raised-button label="回复" class="reply-btn" primary style="float:right" @click="reply(index)" />
                                        </div>
                                    </transition>
                                </mu-list-item>
                            </mu-list>
                            <mu-pagination :total="pageTotal" :current="pageCurrent" @pageChange="handleClick">
                            </mu-pagination>
                        </div>
                    </div>
                </mu-col>
                <mu-col width="100" tablet="30" desktop="30">
                    <div v-if="courseData.hasjoin">
                        <div class="notice-list">
                            <mu-list>
                                <mu-sub-header>公告</mu-sub-header>
                                <mu-list-item>老师还没有发布公告哦~</mu-list-item>
                            </mu-list>
                        </div>
                    </div>
                    <div class="comment-list">
                        <mu-list>
                            <mu-sub-header>评价</mu-sub-header>
                            <div v-if="courseData.hasjoin">
                                <mu-list-item disabled>
                                    打分：
                                    <mu-icon value="grade"/>
                                    <mu-icon value="grade"/>
                                    <mu-icon value="grade"/>
                                    <mu-icon value="grade"/>
                                    <mu-icon value="grade"/>
                                    <mu-text-field hintText="请输入评论内容" multiLine :rows="3" :rowsMax="6"/>
                                    <mu-raised-button label="确定" class="demo-raised-button" primary style="float:right"/>
                                </mu-list-item>

                                <mu-list-item disabled>
                                    我的评分：
                                    <mu-icon value="grade"/>
                                    <mu-icon value="grade"/>
                                    <mu-icon value="grade"/>
                                    <mu-icon value="grade"/>
                                    <mu-icon value="grade"/>
                                </mu-list-item>
                            
                                
                            </div>
                            <mu-list-item :title="comment.name" v-for="comment in courseData.comments" :key="comment.id">
                                <mu-avatar :src="comment.port" slot="leftAvatar" />
                                <mu-icon value="grade" v-for="item in comment.star" :key="item" />
                                <mu-list-item slot="right" class="time">
                                    {{comment.time}}
                                </mu-list-item>
                            </mu-list-item>
                        </mu-list>
                    </div>
                    <div class="learner-list">
                        <mu-list>
                            <mu-sub-header>{{student_num}}人在学</mu-sub-header>
                            <mu-list-item class="learner-avatar" v-for="student in courseData.students" :key="student.id">
                                <mu-avatar slot="left" :src="student.port" />
                            </mu-list-item>
                        </mu-list>
                    </div>
                </mu-col>
            </mu-row>
            <mu-snackbar v-if="snackbar" :message="alertText" @actionClick="hideSnackbar" @close="hideSnackbar" style="text-align:center" />
        </div>
        <v-footer></v-footer>
    </div>
</template>
<script>
import header from 'components/common/header/header.vue'
import footer from 'components/common/footer/footer.vue'
import rightSider from 'components/common/rightSider/rightSider.vue'

import courseHeader from 'entry/courseDetail/courseHeader.vue'


const ERR_OK = 0
import Vue from 'vue'
export default {
    data() {
            return {
                courseData: {},
                activeTab: 'chapter',
                chapter: {},
                bus: new Vue(),
                snackbar: false,
                alertText: '未知错误',
                discussionAreaTitle: '全部主题',
                discussiontype: 1,
                discusionData: [],
                close: false,
                toggleReply: false,
                to: '',
                replyText: '',
                user: {},
                dialog: false,
                s_index: 0,
                addDiscuss: {
                    content: "",
                    type: '1',
                    information_id: 1,
                    title: "",
                    isNick: false
                },
                pageTotal: 100,
                pageCurrent: 1
            }
        },
        components: {
            'v-header': header,
            'v-footer': footer,
            'v-rightSider': rightSider,
            'v-courseHeader': courseHeader
        },
        created() {
            this.setDiscussion()
            this.$http.get('/course/' + this.cid).then((response) => {
                    response = response.body
                        // console.log(response)
                    if (response.failure.length === 0) {
                        this.courseData = response.success
                    } else {
                        alert(response.failure[0])
                    }

                }),
                this.$http.get('/course/' + this.cid + '/section').then((response) => {
                    response = response.body
                        // console.log(response)
                    if (response.failure.length === 0) {
                        this.chapter = response.success
                        console.log(this.chapter)
                    } else {
                        alert(response.failure[0])
                    }

                });
            if (this.bus) {
                const _self = this;
                this.bus.$on("alertFlag", function(msg) {
                    console.log("接收到其他组件传递的数据：");
                    console.log(msg)
                    _self.snackbar = msg.flag;
                    _self.alertText = msg.text;
                    _self.showSnackbar();
                });
            };
            this.$http.get('/user').then((response) => {
                response = response.body
                    // console.log(response)
                if (response.failure.length === 0) {
                    this.user = response.success

                } else {
                    alert(response.failure[0])
                }

            });


        },
        methods: {
            handleUrl() {
                var url = location.search; //获取url中"?"符后的字串 
                var theRequest = new Object();
                if (url.indexOf("?") != -1) {
                    var str = url.substr(1);
                    var strs = str.split("&");
                    for (var i = 0; i < strs.length; i++) {
                        theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
                    }
                }
                return theRequest;
            },
            handleTabChange(val) {
                this.activeTab = val
            },
            handleActive() {
                window.alert('tab active')
            },
            showSnackbar() {
                this.snackbar = true
                if (this.snackTimer) clearTimeout(this.snackTimer)
                this.snackTimer = setTimeout(() => {
                    this.snackbar = false
                }, 1000)
            },
            hideSnackbar() {
                this.snackbar = false
                if (this.snackTimer) clearTimeout(this.snackTimer)
            },
            setDiscussionTitle(val, type) {
                this.discussionAreaTitle = val;
                this.discussiontype = type;
                this.pageCurrent = 1;
                this.setDiscussion();
            },
            setDiscussion() {
                const _self = this;
                this.$http.get('/discuss/' + this.cid + '/course/' + this.discussiontype, {
                    params: {
                        "pageNum": _self.pageCurrent,
                        "pageSize": 10
                    }
                }).then((response) => {
                    response = response.body
                    if (response.failure.length === 0) {
                        _self.discusionData = response.success
                    } else {
                        alert(response.failure[0])
                    }

                })
            },
            setClose(item, index) {
                // alert(item)
                // alert(index)
                if (typeof item.close == 'undefined' || item.close == false) {
                    this.$nextTick(function() {　　　　　　　　
                        this.discusionData.forEach(function(item) {
                            Vue.set(item, 'close', false);　　　　　　　　
                        });　　　　　
                        Vue.set(item, 'close', true);　　
                    });
                } else {
                    item.close = false
                }
            },
            setReply(item, index) {
                if (typeof item.active == 'undefined' || item.active == false) {
                    this.$nextTick(function() {　　　　　　　　
                        this.discusionData.forEach(function(item) {
                            Vue.set(item, 'active', false);　　　　　　　　
                        });　　　　　
                        Vue.set(item, 'active', true);　　
                        this.to = item.employee.name;　
                        this.replyText = '回复@' + item.employee.name + '：'
                    });
                } else {
                    item.active = false
                }
            },
            setSubReply(item) {
                this.to = item.to.name;
                this.replyText = '回复@' + item.to.name + '：'
            },
            setSupport(index) {
                this.discusionData[index].grate++;
            },
            setReplySupport(index, replyIndex) {
                this.discusionData[index].commends[replyIndex].grate++;
            },
            reply(index) {
                const _self = this;
                const today = new Date();
                console.log(_self.replyText)
                this.discusionData[index].commends.push({
                    id: 1,
                    to: {
                        id: 0,
                        name: _self.to,
                        isTeacher: false
                    },
                    time: today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + (today.getDate()),
                    from: {
                        id: 0,
                        name: _self.user.name,
                        port: _self.user.portrait,
                        isTeacher: false
                    },
                    content: _self.replyText,
                    grate: 0
                })
                this.replyText = ''
            },
            open() {
                this.dialog = true
            },
            dialogClose() {
                this.dialog = false
            },
            addDiscussF() {
                const _self = this;
                this.$http.post('/discuss/publish', {
                    params: this.addDiscuss
                }).then((response) => {
                    response = response.body
                    if (response.failure.length === 0) {
                        _self.dialog = false
                        this.alertText = '发帖成功！'
                        this.showSnackbar();
                    } else {
                        alert(response.failure[0])
                    }

                })

            },
            test() {
                console.log(this.addDiscuss)
                console.log(this.s_index)
                console.log(this.addDiscuss.information_id)
            },
            handleClick(newIndex) {
                this.pageCurrent = newIndex;
                this.setDiscussion();
            }
        },
        computed: {
            cid: function() {
                if (typeof this.handleUrl()['id'] == 'undefined') {
                    return 0;
                } else {
                    return this.handleUrl()['id']
                }

            },
            student_num() {
                try {
                    return this.courseData.students.length
                } catch (e) {
                    console.log('数据读取中')

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
    .tab {
        position: relative;
        z-index: 0
    }
    .tab-detail {
        border: 1px solid #ccc;
        padding: 15px;
        // height: 500px;
    }
}

.comment-list {
    border: 1px solid #ccc;
    .time {
        .mu-item {
            display: inline-block;
            padding-right: 80px;
            font-size: 12px;
            color: #ccc;
        }
    }
    .mu-icon {
        font-size: 16px;
        color: #ffc107
    }
}

.learner-list {
    margin-top: 15px;
    border: 1px solid #ccc;
    .learner-avatar {
        float: left;
    }
}

.notice-list{
    margin-bottom: 15px;
    border: 1px solid #ccc;
}
.tab-page {
    display: none;
}

.tab-page.active {
    display: block;
}

.sub-list-title {
    border-bottom: 1px solid #ccc
}

.chapter {
    .add-discussion {
        margin-left: 18px;
        margin-top: 20px;
        margin-bottom: 20px;
    }
}

.hover-state {
    &:hover {
        color: @main-color;
        cursor: pointer;
    }
}

.slide-fade-enter-active {
    transition: all .5s ease;
}

.slide-fade-leave-active {
    transition: all .5s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}

.slide-fade-enter,
.slide-fade-leave-active {
    transform: translateX(10px);
    opacity: 0;
}

.mu-pagination {
    display: flex;
    justify-content: center
}

.chapter-states {
    .mu-item-content {
        display: flex;
    }
    padding-right: 200px;
    span {
        display: inline-block;
        margin-left: 10px;
        white-space: nowrap;
        &.active {
            color: @main-color
        }
    }
}
</style>
