<template>
    <div id="video">
        <div class="container">
            <div id="dplayer1"></div>
        </div>
        <!-- <span style="display:none">{{getDiscussion}}{{test}}</span> -->
    </div>
</template>
<style type="text/css" src="./DPlayer.min.css"></style>
<style lang="less">
#dplayer1 {
    margin-top: 40px;
}
</style>

<script>
// import flv from 'flv.js'

import DPlayer from 'DPlayer';
// let DPlayer = require('DPlayer')

export default {
    data() {
            return {
                dpi: {},
                discussData: [],
                count: 0,
                end_time: 0,
                current: 0,
                lessonData: {}
            }
        },
        props: {

            bus: {
                type: Object
            },
            discussType: {
                type: Number
            },
            autoContinue: {
                type: Boolean
            },
            nextId: {
                type: Number
            }
        },
        mounted() {
            const _self = this;
            // _self.dpi = null;
            // let $dplayer1 = document.getElementById('dplayer1');

        },
        beforeRouteEnter(to, from, next) {
            next(vm => {
                vm.$http.get('/lesson/' + to.params.id).then((response) => {
                    response = response.body
                    if (response.failure.length === 0) {
                        vm.lessonData = response.success
                        vm.dpi = new DPlayer({
                            element: document.getElementById('dplayer1'),
                            autoplay: false,
                            theme: '#FADFA3',
                            loop: false,
                            screenshot: true,
                            hotkey: true,
                            logo: '',
                            video: {
                                url: vm.lessonData.url,
                                pic: vm.lessonData.cover,
                                type: 'hls',
                            },
                            danmaku: {
                                id: '9E2E3368B56CDBB4',
                                api: '/danmaku/' + vm.$route.params.id,
                                // api:'https://api.prprpr.me/dplayer/',
                                token: 'tokendemo',
                                maximum: 3000,
                                user: 'DIYgod的女粉'
                            },
                            contextmenu: [{
                                text: '视频播放遇到问题？',
                                link: 'http://www.baidu.com'
                            }]
                        })
                        console.log('------')
                        console.log(vm.lessonData)
                        const point = vm.handleUrl()['point'];
                        if (typeof point != 'undefined') {
                            vm.dpi.video.current.currentTime = point
                            vm.getDiscussionByPoint(point);
                        }

                        let current = 0,
                            end_count = 0;
                        try {
                            if (vm.end_time == 0 && vm.dpi.video) {
                                vm.end_time = vm.dpi.video.duration;
                            }
                            end_count = parseInt(vm.end_time / 60) * 60
                        } catch (e) {
                            console.log(e)
                        }
                        // 获取总时长
                        setInterval(function() {
                            try {
                                current = vm.dpi.video.current.currentTime;
                                vm.current = current;
                                if (parseInt(current / 60) * 60 == vm.count) {} else if (vm.count <= end_count) {
                                    // 时间点改变，请求数据
                                    vm.getDiscussionByPoint(parseInt(current / 60) * 60)
                                }

                                if (vm.autoContinue && Boolean(vm.end_time) && (current === vm.end_time)) {
                                    console.log('播放结束')
                                        // 请求，判断类型
                                    vm.$http.get('/lesson/' + vm.nextId).then((response) => {
                                        response = response.body
                                            // console.log(response)
                                        if (response.failure.length === 0) {
                                            let type = response.success.type
                                            if (type === 0) {
                                                window.location = '#/video/' + vm.nextId + '?cid=' + vm.handleUrl()['cid']
                                            } else if (type === 1) {
                                                window.location = '#/audio/' + vm.nextId + '?cid=' + vm.handleUrl()['cid']
                                            } else {
                                                window.location = '#/page/' + vm.nextId + '?cid=' + vm.handleUrl()['cid']
                                            }
                                        } else {
                                            alert(response.failure[0])
                                        }

                                    })


                                }
                            } catch (e) {
                                console.log(e)
                            }
                        }, 1000)
                    } else {
                        alert(response.failure[0])
                    }

                })
            })


        },
        created() {
            const _self = this;
            window.onunload = function() {　
                // debugger;
                _self.$http.post('/history/record', {
                    lesson_id: _self.lessonData.id,
                    point: _self.current
                }).then((response) => {
                    response = response.body
                    if (response.failure.length === 0) {
                        console.log('success')
                    } else {
                        alert(response.failure[0])
                    }

                })
            }
        },
        methods: {
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
                        _self.count = point_time;
                        console.log('取数据时间点：' + _self.count)
                        _self.bus.$emit("getDiscuss", {
                            discuss: _self.discussData,
                            point_time: _self.count
                        });
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
            }
        }
}
</script>
