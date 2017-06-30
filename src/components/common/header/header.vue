<template>
    <div>
        <mu-appbar title="企业在线学习平台" class="header" >
            <p @click="toggleDetail">企业在线学习平台</p>
            <!-- <mu-icon-button icon="menu" slot="left"/>
    <mu-flat-button label="expand_more" slot="right"/>
    <mu-flat-button href="333" label="expand_more" slot="right"/> -->
            <mu-text-field icon="search" class="appbar-search-field" slot="right" hintText="请输入搜索内容" />
            <mu-flat-button color="white" label="我的学习" slot="right" />
            <mu-flat-button color="white" label="我的消息" slot="right" />
            <mu-badge :content="''+user.new_info" class="icon-badge" circle secondary slot="right"> </mu-badge>
            <mu-flat-button color="white" :label="user.name" slot="right" />
            <mu-avatar slot="right" :src="user.portrait" />
            <!-- <mu-icon-menu icon="expand_more" slot="right">
            <mu-menu-item title="菜单 1" />
            <mu-menu-item title="菜单 2" />
            <mu-menu-item title="菜单 3" />
            <mu-menu-item title="菜单 4" />
            <mu-menu-item title="菜单 5" />
        </mu-icon-menu> -->
        </mu-appbar>
        <mu-appbar class="header-hide-menu" v-show="detailShow">
            <mu-flat-button color="#666" label="我的学习" slot="left" />
            <mu-flat-button color="#666" label="我的消息" slot="left" />
            <mu-flat-button color="#666" label="$$小仙女" slot="left" />
        </mu-appbar>
    </div>
</template>
<style type="text/css" lang="less">
.header {
    position: fixed;
    top: 0;
}

.header-hide-menu {
    position: fixed;
    top: 60px;
    background: #fff;
}

.mu-appbar-title {
    cursor: pointer;
}

.appbar-search-field {
    color: #FFF;
    margin-bottom: 0;
    &.focus-state {
        color: #FFF;
    }
    .mu-text-field-hint {
        color: fade(#fff, 54%);
    }
    .mu-text-field-input {
        color: #FFF;
    }
    .mu-text-field-focus-line {
        background-color: #FFF;
    }
}

.icon-badge {
    position: relative;
    left: -20px;
    top: -10px;
}
</style>
<script>
// import BScroll from "better-scroll";
// import shopcart from "components/shopcart/shopcart";
// import cartcontrol from "components/cartcontrol/cartcontrol";
const ERR_OK = 0
export default {
    props: {
        
    },
    data() {
        return {
            detailShow: false,
            user:[]
        }
    },
    created() {
        this.$http.get('/user').then((response) => {
            response = response.body
                // console.log(response)
                if (response.failure.length === 0) {
                    this.user = response.success

                } else {
                    alert(response.failure[0])
                }
           
        })
    },
    methods: {
        toggleDetail() {
            if (!this.detailShow) {
                this.detailShow = true
            } else {
                this.detailShow = false
            }

        }
    }
}
</script>
