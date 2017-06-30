<template>
    <div id="app">
        <v-header :unread="unread"></v-header>
        <v-rightSider></v-rightSider>
        <div id="content" class="content">
            <v-search :directions="directions" :generics="generics" :styles="styles" :title="title" @receiveIds="handleIds" :bus="bus"></v-search>
            <v-courseList :tabShow="tabShow" :coursesUrl="coursesUrl" :pagination="pagination" :bus="bus"></v-courseList>
        </div>
        <v-footer></v-footer>
    </div>
</template>
<script>
import header from 'components/common/header/header.vue'
import footer from 'components/common/footer/footer.vue'
import rightSider from 'components/common/rightSider/rightSider.vue'
import courseList from 'components/common/courseList/courseList.vue'

import search from 'entry/courseSearch/search.vue'
const ERR_OK = 0
import Vue from 'vue'
export default {
    data() {
            return {
                tabShow: true,
                unread: "20",
                coursesUrl: '/course',
                pagination: {
                    pageNum: 1,
                    pageSize: 8,
                    pageTotal: 58,
                    direction_id: 0,
                    generic_id: 0,
                    style_id: 0
                },
                title: '',
                directions: [],
                generics: [],
                styles: [],
                bus:new Vue()
            }
        },
        components: {
            'v-header': header,
            'v-courseList': courseList,
            'v-footer': footer,
            'v-rightSider': rightSider,
            'v-search': search
        },
        created() {
            this.title = this.handleUrl()['title'],
                this.$http.get('/direction').then((response) => {
                    response = response.body
                        // console.log(response)
                        if (response.failure.length === 0) {
                            console.log('success')
                            this.directions = response.success
                        } else {
                            alert(response.failure[0])
                        }
                    
                }),
                this.$http.get('/generic').then((response) => {
                    response = response.body
                        // console.log(response)
                        if (response.failure.length === 0) {
                            console.log('success')
                            this.generics = response.success
                            console.log(this.generics)
                        } else {
                            alert(response.failure[0])
                        }
                   
                }),
                this.$http.get('/style').then((response) => {
                    response = response.body
                        // console.log(response)
                        if (response.failure.length === 0) {
                            console.log('success')
                            this.styles = response.success
                        } else {
                            alert(response.failure[0])
                        }
                    
                })




        },
        methods: {
            handleUrl() {
                var url = location.search; //获取url中"?"符后的字串 
                var theRequest = new Object();
                if (url.indexOf("?") != -1) {
                    var str = url.substr(1);
                    var strs = str.split("&");
                    for (var i = 0; i < strs.length; i++) {
                        theRequest[strs[i].split("=")[0]] = decodeURI(strs[i].split("=")[1]);
                    }
                }
                return theRequest;
            },
            handleIds(data) {
                console.log(data)
                this.pagination.direction_id = data.d_id;
                this.pagination.generic_id = data.g_id;
                this.pagination.style_id = data.s_id
            }
        },
}
</script>
<style lang="less">
@main-color: #009688;
.content {
    padding: 50px 100px;
    margin-top: 50px;
}
</style>
