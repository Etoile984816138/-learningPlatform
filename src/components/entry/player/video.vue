<template>
    <div id="video">
        <div class="container">
            <h1>DPlayer</h1>
            <h2>Wow, such a lovely HTML5 danmaku video player</h2>
            <p>Made by <a href="https://www.anotherhome.net/" target="_blank">DIYgod</a>. Available on <a href="https://github.com/DIYgod/DPlayer" target="_blank">GitHub</a>. Licensed SATA.</p>
            <hr>
            <h3>Normal</h3>
            <div id="dplayer1"></div>
            <button onclick="switchDPlayer()">Switch Video</button>
            <button onclick="dp1.notice('Notice演示')">Notice</button>
            <h3>Quality switching</h3>
            <div id="dplayer6"></div>
            <button onclick="dp6.switchQuality(1)">Switch quality</button>
            <h3>Live Video (HTTP Live Streaming, M3U8 format) support</h3>
            <div id="dplayer3"></div>
            <h3>FLV format support</h3>
            <div id="dplayer4"></div>
            <h3>Bilibili video and danmaku support</h3>
            <div id="dplayer2"></div>
            <!--<h3>Segmented videos</h3>
    <div id="dplayer5"></div>-->
        </div>
    </div>
</template>
<style type="text/css" src="./DPlayer.min.css"></style>
<style type="text/css">
	
</style>
<!-- <script type="text/javascript" src="./flv.min.js"></script> -->
<!-- <script type="text/javascript" src="./DPlayer.min.js"></script> -->
<script>
import flv from 'flv.js'
import DPlayer from 'DPlayer';
// console.log(document.getElementById('video'))
// console.log(new DPlayer({
//     element: document.getElementById('video'),
//     video: {
//         url: 'http://devtest.qiniudn.com/若能绽放光芒.mp4',
//         pic: 'http://devtest.qiniudn.com/若能绽放光芒.png',
//         type: 'auto',
//     }
// }))
export default {
    data() {
            return {
                dpi: {}
                // dp1: new DPlayer({
                //     element: document.getElementById('dplayer1'),
                //     autoplay: false,
                //     theme: '#FADFA3',
                //     loop: true,
                //     screenshot: true,
                //     hotkey: true,
                //     logo: 'http://devtest.qiniudn.com/DPlayer.png',
                //     video: {
                //         url: 'http://devtest.qiniudn.com/若能绽放光芒.mp4',
                //         pic: 'http://devtest.qiniudn.com/若能绽放光芒.png',
                //         type: 'auto',
                //     },
                //     danmaku: {
                //         id: '9E2E3368B56CDBB4',
                //         api: 'https://api.prprpr.me/dplayer/',
                //         token: 'tokendemo',
                //         maximum: 3000,
                //         user: 'DIYgod的女粉'
                //     },
                //     contextmenu: [{
                //         text: '关于作者',
                //         link: 'http://diygod.me'
                //     }, {
                //         text: '播放器意见反馈',
                //         link: 'https://github.com/DIYgod/DPlayer/issues'
                //     }, {
                //         text: '关于 DPlayer 播放器',
                //         link: 'https://github.com/DIYgod/DPlayer'
                //     }, {
                //         text: '自定义右键菜单demo',
                //         link: 'https://github.com/DIYgod/DPlayer'
                //     }]
                // })

            }
        },
        // components: {

        // },
        // mounted() {
        //     // const player = this.$refs.player.dp
        //     // player.play()
        //     // setTimeout(() => {
        //     //     player.pause()
        //     // }, 2000)
        // },
        mounted() {
            try {
                this.dpi = new DPlayer({
                    element: document.getElementById('dplayer1'),
                    autoplay: false,
                    theme: '#FADFA3',
                    loop: true,
                    screenshot: true,
                    hotkey: true,
                    logo: 'http://devtest.qiniudn.com/DPlayer.png',
                    video: {
                        url: 'http://devtest.qiniudn.com/若能绽放光芒.mp4',
                        pic: 'http://devtest.qiniudn.com/若能绽放光芒.png',
                        type: 'auto',
                    },
                    danmaku: {
                        id: '9E2E3368B56CDBB4',
                        api: 'https://api.prprpr.me/dplayer/',
                        token: 'tokendemo',
                        maximum: 3000,
                        user: 'DIYgod的女粉'
                    },
                    contextmenu: [{
                        text: '关于作者',
                        link: 'http://diygod.me'
                    }, {
                        text: '播放器意见反馈',
                        link: 'https://github.com/DIYgod/DPlayer/issues'
                    }, {
                        text: '关于 DPlayer 播放器',
                        link: 'https://github.com/DIYgod/DPlayer'
                    }, {
                        text: '自定义右键菜单demo',
                        link: 'https://github.com/DIYgod/DPlayer'
                    }]
                })
            } catch (e) {
                console.log('DOM渲染中')
            }
            // console.log(this.playerOption.danmaku.addition)
        }
}
</script>
