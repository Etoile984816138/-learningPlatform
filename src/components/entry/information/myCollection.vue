<template>
    <div id="myCollection">
        <div class="search-wrapper">
            <mu-text-field icon="search" class="course-list-search-field" v-model="search_text" hintText="请输入搜索内容" />
            <mu-raised-button label="搜索" class="search-button" primary />
        </div>
        <div class="course-list-content">
            <mu-grid-list class="gridlist-demo" :cols="gridrow">
                <mu-grid-tile v-for="courseItem in courses" :key="courseItem.id" style="height:300px">
                    <img :src="courseItem.cover" class="cover"  @click="toDetail(courseItem.id)"/>
                    <img src="./banner1.jpg" class="cover" />
                    <span slot="title">{{courseItem.title}}</span>
                    <span slot="subTitle">{{courseItem.number}}人收藏</span>
                </mu-grid-tile>
            </mu-grid-list>
        </div>
        <mu-pagination :total="pageTotal*10" :current="pageNum" @pageChange="pageClick">
        </mu-pagination>
    </div>
</template>
<script>
export default {

}
</script>
<style lang="less">
#myCollection {
    .search-wrapper {
        float: right;
        z-index: -1;
        position: relative
    }
    .course-list-content {
        clear: both;
    }
    // @main-color: #009688;
    .course-list {
        margin-bottom: 20px;
        position: relative;
        width: 300px;
        height: 200px;
        img {
            // position: absolute;
            width: 100%;
            height: 100%;
        }
    }
    .mu-grid-tile{
        cursor: pointer;
    }
    .mu-pagination {
        margin-top: 30px;
        display: flex;
        justify-content: center
    }
}
</style>
<script type="text/javascript">
export default {
    data() {
            return {
                test: '',
                courses: [],
                search_text: '',
                gridrow: 3,
                pageTotal: 50,
                pageNum: 1
            }
        },
        created() {
            this.viewCourse(this.pageNum)

        },
        methods: {
            pageClick(newIndex) {
                this.viewCourse(newIndex)
            },
            viewCourse(current) {
                const _self = this
                this.$http.get('/user/collection', {
                    params: {
                        pageNum: current,
                        pageSize: 12,
                    }
                }).then((response) => {
                    response = response.body
                        // console.log(response)
                    if (response.failure.length === 0) {
                        console.log(response)
                        this.courses = response.success
                        this.pageTotal = response.total
                    } else {
                        alert(response.failure[0])
                    }

                })
            },
            toDetail(id) {
                window.location.href = '/module/courseDetail.html?cid=' + id
            }
        }
}
</script>
