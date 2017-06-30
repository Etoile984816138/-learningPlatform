<template>
    <div id="app">
        <v-header></v-header>
        <v-rightSider></v-rightSider>
        <div id="content" class="content">
            <p class="content-header">意见反馈</p>
            <div class="content-radio-wraaper">
                <mu-radio label="内容意见" name="advice" nativeValue="1" v-model="radioValue" class="content-radio" />
                <mu-radio label="产品意见" name="advice" nativeValue="2" v-model="radioValue" class="content-radio" />
                <mu-radio label="技术问题" name="advice" nativeValue="3" v-model="radioValue" class="content-radio" />
                <mu-radio label="其它" name="advice" nativeValue="4" v-model="radioValue" class="content-radio" />
            </div>
            <mu-text-field hintText="不允许超过400个字符" :errorText="multiLineInputErrorText" @textOverflow="handleMultiLineOverflow" multiLine :rows="10" :rowsMax="8" :maxLength="400" :value="textarea" />
            <div class="upload-wrapper">
                <div class="upload-img-wrapper" v-if="imgUrls.length" v-for="item in imgUrls"><img :src="item" :alt="item"></div>
                <!-- <p v-else></p> -->
                <mu-raised-button class="upload-btn" label="+">
                    <input type="file" class="file-button">
                </mu-raised-button>
                <!-- <div class="upload-btn">+</div> -->
            </div>
            <p class="prompt-text">最多可以上传5张图片，图片大小不能超过2M</p>
            <mu-text-field label="相关页面地址" labelFloat :value="urlText" />
            <mu-raised-button class="submit-button" label="提交" primary/>
            <div></div>
        </div>
        <v-footer></v-footer>
    </div>
</template>
<style lang="less">
@main-color: #009688;
.content {
    padding: 50px 100px;
    margin-top: 50px;
    margin-bottom: 100px;
    .mu-text-field {
    width: 100%
}
}

.content-radio-wrapper {
    display: flex;
}

.content-radio {
    margin-right: 30px;
}



.file-button {
    position: absolute;
    left: 0;
    right: 0;
    top: 0;
    bottom: 0;
    opacity: 0;
}

.upload-img-wrapper {
    display: inline-block;
    width: 100px;
    height: 100px;
    border: 1px solid #ccc;
    vertical-align: top;
    margin-right: 20px;
    img {
        width: 100%;
        height: 100%
    }
}

.upload-btn {
    display: inline-block;
    width: 100px;
    height: 100px;
    font-size: 40px;
    text-align: center;
    border: 1px solid #ccc;
    color: #ccc;
    cursor: pointer;
    &:hover {
        color: @main-color
    }
}

.prompt-text {
    color: #ccc;
}

.submit-button {
    float: right;
    margin-top: 50px;
}
</style>
<script>
import header from 'components/common/header/header.vue'
import footer from 'components/common/footer/footer.vue'
import rightSider from 'components/common/rightSider/rightSider.vue'

const ERR_OK = 0;

export default {
    name: 'app',
    components: {
        'v-header': header,
        'v-footer': footer,
        'v-rightSider': rightSider,
    },
    data() {
        return {
            radioValue: "1",
            input: '',
            inputErrorText: '',
            multiLineInput: '',
            multiLineInputErrorText: '',
            urlText: '',
            imgUrls: ['1', '2', '3'],
            imgText: '最多可以上传5张图片，图片大小不能超过2M',
            textarea: '你猜'

        }
    },
    created() {
        console.log(this.radioValue)
    },
    methods: {
        handleInputOverflow(isOverflow) {
            this.inputErrorText = isOverflow ? '超过啦！！！！' : ''
        },
        handleMultiLineOverflow(isOverflow) {
            this.multiLineInputErrorText = isOverflow ? '超过啦！！！！' : ''
        },
        submit() {
            this.$http.post('/option', {
                params: {
                    type: parseInt(this.radioValue),
                    content: this.textarea,
                    img: this.imgUrls,
                    url: this.urlText
                }
            }).then((response) => {
                response = response.body
                    // console.log(response)
                    if (response.failure.length === 0) {
                        console.log('success')
                        this.directions = response.success
                    } else {
                        alert(response.failure[0])
                    }
               
            })
        }
    },
    render: function(createElement) {
        // if (this.imgUrls.length) {
        //     return createElement('ul', this.imgUrls.map(function(item) {
        //         return createElement('li', item.name)
        //     }))
        // }
    }
}
</script>
