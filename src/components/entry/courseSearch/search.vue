<!-- 
    逻辑概述：1.点击all-tag，其它tag上active去掉
            2.点击tag，all-tag上active去掉
 -->
<template>
    <div id="search" class="search-wrapper">
        <div class="search-wrapper-li">
            <p class="search-title">方向：{{setGeneric}}</p>
            <mu-list class="search-mu-list" @change="choiseDirection" :value="direction_id">
                <mu-list-item value="0" title="全部"></mu-list-item>
                <mu-list-item v-for="direction in direction_arr" :value="direction.d_id" :title="direction.name" :key="direction.d_id">
                </mu-list-item>
            </mu-list>
        </div>
        <div class="search-wrapper-li">
            <p class="search-title">分类：{{setStyle}}</p>
            <mu-list class="search-mu-list" @change="choiseGeneric" :value="generic_id">
                <mu-list-item value="0" title="全部"></mu-list-item>
                <mu-list-item v-for="generic in generic_arr" :value="generic.g_id" :title="generic.name" :key="generic.g_id">
                </mu-list-item>
            </mu-list>
        </div>
        <div class="search-wrapper-li">
            <p class="search-title">类型：</p>
            <mu-list class="search-mu-list" @change="choiseStyle" :value="style_id">
                <mu-list-item value="0" title="全部"></mu-list-item>
                <mu-list-item v-for="style in style_arr" :value="style.s_id" :title="style.name" :key="style.s_id">
                </mu-list-item>
            </mu-list>
        </div>
    </div>
</template>
<script>
const ERR_OK = 0;
export default {
    props: {
        searchCondition: {
            type: Object
        },
        directions: {
            type: Array
        },
        generics: {
            type: Array
        },
        styles: {
            type: Array
        },
        title: {
            type: String
        },
        bus: {
            type: Object
        }
    },
    data() {
        return {
            isActive: true,
            detailShow: true,
            direction_id: 0,
            generic_id: 0,
            style_id: 0,
            generic_arr:[],
            style_arr:[]
            // direction_arr: this.directions,
            // generic_arr: this.generics,
            // style_arr: this.styles

        }
    },
    methods: {
        showDetail() {
            this.detailShow = true
        },
        hideDetail() {
            this.detailShow = false
        },
        choiseDirection(val) {
            this.direction_id = val;
            const _self = this;
            this.$http.get('/direction/' + _self.direction_id).then((response) => {
                response = response.body
                if (response.failure.length === 0) {
                    _self.generic_arr = response.success

                } else {
                    alert(response.failure[0])
                }

            });
            this.bus.$emit("receiveIds", {
                d_id: _self.direction_id,
                g_id: _self.generic_id,
                s_id: _self.style_id
            });
            // alert(111)

        },
        choiseGeneric(val) {
            const _self = this;
            this.generic_id = val;

            // 根据分类获取类型
            this.$http.get('/generic/' + _self.generic_id).then((response) => {
                response = response.body
                if (response.failure.length === 0) {
                    _self.style_arr = response.success
                } else {
                    alert(response.failure[0])
                }

            });
            this.bus.$emit("receiveIds", {
                d_id: _self.direction_id,
                g_id: _self.generic_id,
                s_id: _self.style_id
            });
            // alert(222)

        },
        choiseStyle(val) {
            const _self = this;
            this.style_id = val
            this.bus.$emit("receiveIds", {
                d_id: _self.direction_id,
                g_id: _self.generic_id,
                s_id: _self.style_id
            });
        },


    },
    mounted() {
        // this.test = this.generics;
    },
    computed: {
        direction_arr: function() {
            return this.directions
        },
        setGeneric: function() {
            this.generic_arr = this.generics
        },
        setStyle: function() {
            this.style_arr = this.styles
        }
    }


}
</script>
<style lang="less">
@main-color: #009688;
.search-wrapper {
    padding: 20px;
    border: 1px solid #ccc;
}

.search-mu-list {
    display: flex;
    font-size: 12px;
    flex-wrap: wrap;
}

.search-title {
    display: block;
    white-space: nowrap;
    line-height: 2;
    font-size: 16px
}

.search-wrapper-li {
    display: flex;
}

.tag,
.all-tag {
    display: inline-block;
    padding: 5px 10px;
    cursor: pointer;
    &:hover {
        color: @main-color;
    }
    &.active {
        background: @main-color;
        color: #fff;
    }
}
</style>
