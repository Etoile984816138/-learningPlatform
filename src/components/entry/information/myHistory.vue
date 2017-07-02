<template>
    <div id="myHistory">
        <div class="search-wrapper">
            <mu-text-field icon="search" class="course-list-search-field" v-model="search_text" hintText="请输入搜索内容" />
            <mu-raised-button label="搜索" class="search-button" primary />
        </div>
        <div class="course-list-content">
        	<div class="day-course" v-for="info in courses">
	        	<p class="content-title">{{info.time}}</p>
	            <mu-grid-list class="gridlist-demo" :cols="gridrow">
	                <mu-grid-tile v-for="courseItem in info.list" :key="courseItem.lesson.id">
	                    <img :src="courseItem.cover" class="cover" />
	                    <!-- <img src="./banner1.jpg" class="cover" /> -->
	                    <span slot="title">{{courseItem.lesson.title}}</span>

	                    <span slot="subTitle" v-if="courseItem.lesson.material.type != 2 && courseItem.lesson.material.length != courseItem.point">看至{{courseItem.point}}</span>
	                    <span slot="subTitle" v-else-if="courseItem.lesson.material == 2 && courseItem.lesson.material.length != courseItem.point">看至{{courseItem.point}}页</span>
	                    <span slot="subTitle" v-else>已看完</span>

	                    <span slot="subTitle" class="sub-title-right" style="" v-if="courseItem.lesson.material.type != 2">视频时长：{{courseItem.lesson.material.length}}</span>
	                    <span v-else>页数：{{courseItem.lesson.material.page}}页</span>

	                </mu-grid-tile>
	            </mu-grid-list>
            </div>
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
@main-color: #009688;
#myHistory {
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
    .mu-pagination {
        margin-top: 30px;
        display: flex;
        justify-content: center
    }
    .sub-title-right{
    	float:right;
    	padding-right:10px;
    }
    .content-title {
    margin: 40px 0 20px 0;
    padding: 5px ;
    border-left: 5px solid @main-color;
    font-size: 16px;
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
                this.$http.get('/user/history', {
                    params: {
                        pageNum: current,
                        pageSize: 100,
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
            }
        }
}
</script>
