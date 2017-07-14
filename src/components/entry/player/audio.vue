<template>
    <div id="audio">
        <div class="container">
            <div id="dplayer2"></div>
        </div>
        <div class="subtitle">{{lessonData.subtitle}}</div>
        <!-- <span style="display:none">{{getDiscussion}}</span> -->
    </div>
</template>
<style type="text/css" src="./DPlayer.min.css"></style>
<style lang="less">
#audio {
    position: relative;
    #dplayer2 {
        margin-top: 40px;
    }
    .subtitle {
        position: absolute;
        top: 0;
        padding: 30px;
        width: 100%;
        height: 375px;
        color: #fff;
        background: rgba(0, 0, 0, 0.7);
        text-align: center;
        overflow: auto;
    }
}
</style>
<!-- <script type="text/javascript" src="./hls.min.js"></script> -->
<!-- <script type="text/javascript" src="./DPlayer.min.js"></script> -->
<script>
// import flv from 'flv.js'
import hls from "./hls.min.js";
import DPlayer from 'DPlayer';

export default {
    data() {
            return {
                dpi: null,
                discussData: [],
                count: 0,
                end_time: 0,
                current: 0,
                lessonData: {}
            }
        },
        props: {
            // lessonData: {
            //     type: Object
            // },
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
        // mounted() {
        //     const _self = this;
        //     try {
        //         this.dpi = new DPlayer({
        //             element: document.getElementById('dplayer2'),
        //             autoplay: false,
        //             theme: '#FADFA3',
        //             loop: false,
        //             screenshot: true,
        //             hotkey: true,
        //             logo: '',
        //             // video: {
        //             //     url: 'http://devtest.qiniudn.com/若能绽放光芒.mp4',
        //             //     pic: 'http://devtest.qiniudn.com/若能绽放光芒.png',
        //             //     type: 'auto',
        //             // },

        //             // video: {
        //             //     quality: [{
        //             //         name: '高清',
        //             //         url: _self.lessonData.url,
        //             //         type: 'hls'
        //             //     }, {
        //             //         name: '超清',
        //             //         url: _self.lessonData.url,
        //             //         type: 'hls'
        //             //     }],
        //             //     defaultQuality: 0,
        //             //     pic: 'http://devtest.qiniudn.com/若能绽放光芒.png'
        //             // },
        //             video: {
        //                 url: _self.lessonData.url,
        //                 pic: _self.lessonData.cover,
        //                 type: 'hls',
        //             },
        //             // danmaku: {
        //             //     id: '9E2E3368B56CDBB4cdere',
        //             //     api: '/danmaku',
        //             //     // api:'https://api.prprpr.me/dplayer/',
        //             //     token: 'tokendemo2',
        //             //     maximum: 3000,
        //             //     user: 'DIYgod的女粉'
        //             // },
        //             contextmenu: [{
        //                 text: '视频播放遇到问题？',
        //                 link: 'http://www.baidu.com'
        //             }]
        //         })
        //         const point = _self.handleUrl()['point'];
        //         if (typeof point != 'undefined') {
        //             // _self.dpi.play(_self.handleUrl()['point'])
        //             _self.dpi.video.current.currentTime = point
        //             _self.getDiscussionByPoint(point);
        //         }

        //     } catch (e) {
        //         console.log('DOM渲染中')
        //     }
        // },
        created() {
            const _self = this;
            window.onunload = function() {　
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
        },
        computed: {
            // getDiscussion() {
            //     const _self = this;
            //     let current = 0,
            //         end_count = 0;
            //     try {
            //         if (_self.end_time == 0) {
            //             _self.end_time = _self.dpi.video.duration;
            //         }
            //         end_count = parseInt(_self.end_time / 60) * 60
            //     } catch (e) {
            //         console.log(e)
            //     }


            //     // _self.dpi.on('current', 'ended', () => {
            //     //         alert('播放结束')
            //     //     })
            //     // 获取总时长
            //     setInterval(function() {
            //         try {
            //             // console.log(_self.dpi)
            //             // console.log(_self.dpi.video.current)
            //             current = _self.dpi.video.current.currentTime;
            //             _self.current = current;
            //             // while (count != end_count) {
            //             if (parseInt(current / 60) * 60 == _self.count) {
            //                 // console.log('时间点未变，不请求数据')
            //             } else if (_self.count <= end_count) {
            //                 // 时间点改变，请求数据
            //                 _self.getDiscussionByPoint(parseInt(current / 60) * 60)
            //             }

            //             if (_self.autoContinue && Boolean(_self.end_time) && (current === _self.end_time)) {
            //                 console.log('播放结束')
            //                     // 请求，判断类型
            //                 _self.$http.get('/lesson/' + _self.nextId).then((response) => {
            //                     response = response.body
            //                         // console.log(response)
            //                     if (response.failure.length === 0) {
            //                         let type = response.success.type
            //                         if (type === 0) {
            //                             window.location = '#/video/' + _self.nextId + '?cid=' + self.handleUrl()['cid']
            //                         } else if (type === 1) {
            //                             window.location = '#/audio/' + _self.nextId + '?cid=' + self.handleUrl()['cid']
            //                         } else {
            //                             window.location = '#/page/' + _self.nextId + '?cid=' + self.handleUrl()['cid']
            //                         }
            //                     } else {
            //                         alert(response.failure[0])
            //                     }

            //                 })

            //             }
            //         } catch (e) {
            //             console.log(e)
            //         }
            //     }, 1000)
            // }
        },
        beforeRouteEnter(to, from, next) {
            next(vm => {
                vm.$http.get('/lesson/' + to.params.id).then((response) => {
                    response = response.body
                    if (response.failure.length === 0) {
                        vm.lessonData = response.success
                        vm.dpi = new DPlayer({
                            element: document.getElementById('dplayer2'),
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
                            contextmenu: [{
                                text: '视频播放遇到问题？',
                                link: 'http://www.baidu.com'
                            }]
                        })
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

        }
}
</script>
