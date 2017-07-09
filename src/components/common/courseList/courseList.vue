<template>
    <div id="courseList" class="course-list">
        <div class="course-list-header">
            <mu-row gutter>
                <mu-col width="100" tablet="50" desktop="33">
                    <div class="tab" v-if="tabShow">
                        <mu-tabs :value="activeTab" @change="handleTabChange">
                            <mu-tab value="/course/new" title="最新" />
                            <mu-tab value="/course/hot" title="最热" />
                            <mu-tab value="/course/commendations" title="最适合我" />
                        </mu-tabs>
                    </div>
                </mu-col>
                <mu-col width="100" tablet="50" desktop="33">
                    <mu-row>
                        <mu-col width="100" tablet="70" desktop="70">
                            <mu-text-field icon="search" class="course-list-search-field" v-model="search_text" hintText="请输入搜索内容" />
                        </mu-col>
                        <mu-col width="100" tablet="30" desktop="30">
                            <mu-raised-button label="搜索" class="search-button" primary @click="search" />
                        </mu-col>
                    </mu-row>
                    <!-- <mu-float-button label="搜索" class="demo-float-button" mini="true"/> -->
                </mu-col>
            </mu-row>
        </div>
        <div class="course-list-content">
            <mu-row gutter>
                <mu-col width="50" tablet="30" desktop="25" v-for="course in courses" :key="course.id">
                    <img :src="course.cover" @click="toDetail(course.id)">
                    <p>{{course.title}}</p>
                </mu-col>
            </mu-row>
        </div>
        <mu-pagination :total="pagination.pageTotal*10" :current="pagination.pageNum" @pageChange="pageClick" v-if="pageShow">
        </mu-pagination>
    </div>
</template>
<style lang="less">
@main-color: #009688;
.course-list {
    margin-top: 20px;
    .course-list-content div[class*="col-"] {
        background: #fff;
        text-align: center;
        color: #000;
        border: 1px solid #ddd;
        padding: 8px;
        margin-bottom: 8px;
        cursor: pointer;
        img {
            width: 100%;
            height: 200px
        }
    }
    .tab {
        position: relative;
        z-index: 0;
    }
    .course-list-search-field {
        float: right;
    }
    .mu-pagination {
        display: flex;
        justify-content: center
    }
    .search-button {
        float: right;
    }
}
</style>
<script>
// import BScroll from "better-scroll";
// import shopcart from "components/shopcart/shopcart";
// import cartcontrol from "components/cartcontrol/cartcontrol";
const ERR_OK = 0
export default {
    props: {
        tabShow: {
            type: Boolean
        },
        coursesUrl: {
            type: String
        },
        pagination: {
            type: Object
        },
        bus: {
            type: Object
        },
        pageShow: {
            type: Boolean
        }
    },
    data() {
        return {
            activeTab: '/course/new',
            courses: [],
            search_text: '',
            search_link: '',
            d_id: 0,
            g_id: 0,
            s_id: 0,
            url:this.coursesUrl
        }
    },
    created() {
        const that = this;
        this.viewCourse(that.pagination.pageNum);

        // console.log(bus)
        if (this.bus) {
            this.bus.$on("receiveIds", function(msg) { //通过$on("事件名称",function(参数列表){})来监听一个事件是否进行处理
                // 接收到数据之后，就可以更新组件中使用的数据，然后展示到页面上
                console.log("接收到其他组件传递的数据：");
                console.log(msg)
                this.d_id = msg.d_id;
                this.g_id = msg.g_id;
                this.s_id = msg.s_id;
                that.viewCourse(1);
            });
        }

    },
    watch: {

    },
    methods: {
        handleTabChange(val) {
            this.activeTab = val
            this.url = val
            this.viewCourse(1)
        },
        handleActive() {
            window.alert('tab active')
        },
        pageClick(newIndex) {
            const _self = this;
            this.viewCourse(newIndex)
        },
        viewCourse(current) {
            const _self = this
            console.log(this.pagination.pageTotal)
            console.log('------')
            console.log(this.url)
            this.$http.get(_self.url, {
                params: {
                    s_id: this.s_id,
                    g_id: this.g_id,
                    d_id: this.d_id,
                    pageNum: current,
                    pageSize: this.pagination.pageSize,
                    title: this.search_text
                }
            }).then((response) => {
                response = response.body
                console.log('------')
                console.log(response)
                if (response.failure.length === 0) {
                    console.log('success')
                    this.courses = response.success
                } else {
                    alert(response.failure[0])
                }

            })
        },
        search() {
            const _self = this;
            console.log(this.search_text)
            if (window.location.pathname == '/module/courseSearch.html') {
                this.viewCourse(_self.pagination.pageNum)

            } else {
                window.location.href = '/module/courseSearch.html?title=' + encodeURI(this.search_text)
            }
        },
        toDetail(id) {
            window.location.href = '/module/courseDetail.html?cid=' + id
        }
    },
    computed: {
    }

}
</script>
