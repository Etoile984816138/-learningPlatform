<template>
    <div id="page">
        <div class="container">
            <!--  src="https://cdn.mozilla.net/pdfjs/tracemonkey.pdf"-->
            <pdf :src="lessonData.url" :page="page" @numPages="numPages = $event"></pdf>
        </div>
        <div class="function-wrapper">
            <mu-icon-button tooltip="上一页" icon="keyboard_arrow_left" @click="minusPage" />
            <input type="number" v-model.number="page" class="pdf_current_page">
            <span>/{{numPages}}</span>
            <mu-icon-button tooltip="下一页" icon="keyboard_arrow_right" @click="addPage" />
            <span class="margin">前往</span>
            <input type="number" v-model.number="goNum">
            <span>页</span>
            <mu-icon-button tooltip="前往" icon="trending_flat" @click="goPage" />
        </div>
    </div>
</template>
<style lang="less">
@main-color: #009688;
#page {
    .container {
        width: 100%;
        height: 500px;
        overflow: auto;
        iframe {
            display: block;
            margin: 0 auto;
            width: 700px;
            height: 500px;
        }
    }
    .function-wrapper {
        position: relative;
        top: -60px;
        display: flex;
        justify-content: center;
        align-items: center;
        input {
            padding: 5px;
            margin: 0 10px;
            width: 48px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }
        span,
        input {
            display: inline-block;
            vertical-align: top;
        }
        .mu-icon-button {
            padding: 0;
            color: @main-color;
        }
        .margin {
            margin-left: 30px;
        }
    }
}
</style>
<!-- <script type="text/javascript" src="./flv.min.js"></script> -->
<!-- <script type="text/javascript" src="./DPlayer.min.js"></script> -->
<script>
import pdf from 'vue-pdf'

export default {
    data() {
            return {
                page: 1,
                numPages: 0,
                goNum: 1
            }
        },
        props: {
            lessonData: {
                type: Object
            },
            nextId: {
                type: Number
            }
        },
        mounted() {

        },
        created() {
            // console.log()
            // /lesson/pdf/{url}
            const _self = this;
            
            const point = this.handleUrl()['point'];
            if (typeof point != 'undefined') {
                this.page = parseInt(point);
            }
            window.onunload = function() {　
                this.$http.post('/history/record', {
                    params: {
                        lesson_id: _self.lessonData.id,
                        point: _self.page
                    }
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
            addPage() {
                if (this.page < this.numPages) {
                    this.page++;
                }
            },
            minusPage() {
                if (this.page > 0) {
                    this.page--;
                }
            },
            goPage() {
                this.page = this.goNum;
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
        components: {
            pdf
        }
}
</script>
