<template>
    <div id="app">
        <v-header></v-header>
        <v-rightSider></v-rightSider>
        <div id="content" class="content">
            <mu-paper :zDepth="1">
                <mu-list-item disabled>
                    <div class="avatar-wrapper">
                        <img src="./banner1.jpg">
                    </div>
                    <div class="infor">
                        <h2>头像</h2>
                        <p>学习时长：20min</p>
                    </div>
                </mu-list-item>
            </mu-paper>
            <mu-row gutter>
                <mu-col width="100" tablet="20" desktop="20">
                    <mu-list class="sidebar" activeClass="active">
                        <mu-list-item title="我的学习" to="/study">
                        </mu-list-item>
                        <mu-list-item title="历史记录" to="/history">
                        </mu-list-item>
                        <mu-list-item title="我的笔记" to="/note">
                        </mu-list-item>
                        <mu-list-item title="我的消息" to="/msg">
                        </mu-list-item>
                        <mu-list-item title="我的收藏" to="/collection">
                        </mu-list-item>
                    </mu-list>
                </mu-col>
                <mu-col width="100" tablet="80" desktop="80" class="right-content">
                    <transition name="slide-fade">
                        <router-view></router-view>
                    </transition>
                </mu-col>
            </mu-row>
            <!-- <router-link to="/study">Go to Foo</router-link> -->
            <!-- <router-link to="/history">Go to Bar</router-link> -->
        </div>
        <v-footer></v-footer>
    </div>
</template>
<script>
import header from 'components/common/header/header.vue'
import footer from 'components/common/footer/footer.vue'
import rightSider from 'components/common/rightSider/rightSider.vue'

import myStudy from 'entry/information/myStudy'
import myHistory from 'entry/information/myHistory'
import myNote from 'entry/information/myNote'
import myMsn from 'entry/information/myMsn'
import myCollection from 'entry/information/myCollection'


export default {
    data() {
            return {
                test: ''


            }
        },
        created() {


        },
        components: {
            'v-header': header,
            'v-footer': footer,
            'v-rightSider': rightSider,
            'v-study': myStudy,
            'v-history': myHistory,
            'v-note': myNote,
            'v-msn': myMsn,
            'v-collection': myCollection
        }
}
</script>
<style lang="less">
@main-color: #009688;
.content {
    margin-top: 65px;
    .avatar-wrapper {
        width: 100px;
        height: 100px;
        border-radius: 100%;
        float: left;
        img {
            width: 100%;
            height: 100%;
            border-radius: 100%;
        }
    }
    .infor {
        padding-left: 120px;
    }
    .sidebar {
        padding: 0;
        // border-right: 1px solid @main-color;
        // background: @main-color;
        .router-link-active {
            background: @main-color;
            .mu-item-title {
                color: #fff;
            }
            color: #fff;
        }
        .mu-list-item {}
        .mu-item-title {
            text-align: center;
            color: #666;
        }
    }
    .right-content{
        padding: 20px 100px 20px 0;
    }
}

.content-title p {
    margin: 40px 0 20px 0;
    padding: 5px;
    border-left: 5px solid @main-color;
    font-size: 16px;
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
</style>
