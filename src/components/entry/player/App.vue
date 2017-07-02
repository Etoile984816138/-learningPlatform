<template>
    <div id="app">
        <v-header></v-header>
        <v-rightSider></v-rightSider>
        <div id="content" class="content">
            <v-video></v-video>
        </div>
        <v-footer></v-footer>
    </div>
</template>
<script>
import header from 'components/common/header/header.vue'
import footer from 'components/common/footer/footer.vue'
import rightSider from 'components/common/rightSider/rightSider.vue'

import video from 'entry/player/video.vue'

export default {
    data() {
            return {
                
            }
        },
        created() {
            // this.$http.get('/direction').then((response) => {
            //         response = response.body
            //             // console.log(response)
            //             if (response.failure.length === 0) {
            //                 console.log('success')
            //                 this.directions = response.success
            //             } else {
            //                 alert(response.failure[0])
            //             }

            //     })

        },
        components: {
            'v-header': header,
            'v-footer': footer,
            'v-rightSider': rightSider,
            'v-video': video
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
</style>
