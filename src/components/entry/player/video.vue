<template>
    <div id="video">
        <div class="container">
            <div id="dplayer1"></div>
        </div>
        <span style="display:none">{{getDiscussion}}</span>
    </div>
</template>
<style type="text/css" src="./DPlayer.min.css"></style>
<style lang="less">
#dplayer1 {
    margin-top: 40px;
}
</style>
<!-- <script type="text/javascript" src="./hls.min.js"></script> -->
<!-- <script type="text/javascript" src="./DPlayer.min.js"></script> -->
<script>
// import flv from 'flv.js'
// import hls from "hls.js";
import DPlayer from 'DPlayer';

export default {
    data() {
            return {
                dpi: {},
                discussData: [],
                count: 0,
                end_time: 0,
                current: 0
            }
        },
        props: {
            lessonData: {
                type: Object
            },
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
            try {
                this.dpi = new DPlayer({
                    element: document.getElementById('dplayer1'),
                    autoplay: false,
                    theme: '#FADFA3',
                    loop: false,
                    screenshot: true,
                    hotkey: true,
                    logo: '',
                    // video: {
                    //     url: 'http://devtest.qiniudn.com/若能绽放光芒.mp4',
                    //     pic: 'http://devtest.qiniudn.com/若能绽放光芒.png',
                    //     type: 'auto',
                    // },

                    video: {
                        quality: [{
                            name: '高清',
                            url: _self.lessonData.url,
                            type: 'hls'
                        }, {
                            name: '超清',
                            url: _self.lessonData.url,
                            type: 'hls'
                        }],
                        defaultQuality: 0,
                        pic: _self.lessonData.cover,
                    },
                    // video: {
                    //     url: 'http://115.159.42.117/oflaDemo/DPlayer-master/demo/test.m3u8',
                    //     pic: 'http://devtest.qiniudn.com/若能绽放光芒.png',
                    //     type: 'hls',
                    // },
                    danmaku: {
                        id: '9E2E3368B56CDBB4cd',
                        api: '/danmaku/' + _self.lessonData.id,
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
                const point = _self.handleUrl()['point'];
                if (typeof point != 'undefined') {
                    // _self.dpi.play(_self.handleUrl()['point'])
                    _self.dpi.video.current.currentTime = point
                    _self.getDiscussionByPoint(point);
                }
            } catch (e) {
                console.log('DOM渲染中')
            }
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
        },
        computed: {
            getDiscussion() {
                const _self = this;
                let current = 0,
                    end_count = 0;
                try {

                    if (_self.end_time == 0) {
                        _self.end_time = _self.dpi.video.duration;
                    }
                    end_count = parseInt(end_time / 60) * 60
                } catch (e) {
                    console.log('DOM渲染中')
                }

                // 获取总时长
                setInterval(function() {
                    try {
                        // console.log('-----')
                        // console.log(_self.dpi.video.current)
                        current = _self.dpi.video.current.currentTime;
                        _self.current = current;
                        // while (count != end_count) {
                        if (parseInt(current / 60) * 60 == _self.count) {
                            // console.log('时间点未变，不请求数据')
                        } else if (_self.count <= end_count) {
                            // 时间点改变，请求数据
                            _self.getDiscussionByPoint(parseInt(current / 60) * 60)
                        }

                        if (_self.autoContinue && Boolean(_self.end_time) && (current === _self.end_time)) {
                            console.log('播放结束')
                                // 请求，判断类型
                            _self.$http.get('/lesson/' + _self.nextId).then((response) => {
                                response = response.body
                                    // console.log(response)
                                if (response.failure.length === 0) {
                                    let type = response.success.type
                                    if (type === 0) {
                                        window.location = '#/video/' + _self.nextId + '?cid=' + self.handleUrl()['cid']
                                    } else if (type === 1) {
                                        window.location = '#/audio/' + _self.nextId + '?cid=' + self.handleUrl()['cid']
                                    } else {
                                        window.location = '#/page/' + _self.nextId + '?cid=' + self.handleUrl()['cid']
                                    }
                                } else {
                                    alert(response.failure[0])
                                }

                            })

                            // _self.$route.params.id = 2

                        }
                    } catch (e) {
                        console.log(e)
                    }
                }, 1000)
            }
        },
        watch: {

        }
}
</script>
