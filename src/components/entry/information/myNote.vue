<template>
    <div id="myNote">
        <div class="tab-wrapper">
            <mu-tabs :value="activeTab" @change="handleTabChange">
                <mu-tab value="tab1" title="划重点" @active="handleActive(1)" />
                <mu-tab value="tab2" title="有疑问" @active="handleActive(2)" />
            </mu-tabs>
        </div>
        <div class="search-wrapper">
            <mu-text-field icon="search" class="course-list-search-field" v-model="search_text" hintText="请输入搜索内容" />
            <mu-raised-button label="搜索" class="search-button" primary />
        </div>
        <div class="course-list-content">
            <div>
                <mu-row gutter v-for="(course,index) in courses" class="row" :key="index">
                    <mu-col width="100" tablet="20" desktop="20"  >
                        <div class="course-list-left" @click="toDetail(course)">
                            <img :src="course.lesson.material.cover">
                            <p class="total-time" v-if="course.lesson.material.type != 2">{{course.lesson.material.length}}</p>
                            <p class="total-time" v-else>{{course.lesson.material.page}}页</p>
                            <p class="current-time">{{course.point_time}}</p>
                        </div>
                        <p>{{course.lesson.title}}</p>
                    </mu-col>
                    <mu-col width="100" tablet="80" desktop="80">{{course.content}}</mu-col>
                </mu-row>
            </div>
        </div>
        <mu-pagination :total="pageTotal*10" :current="pageNum" @pageChange="pageClick">
        </mu-pagination>
    </div>
</template>
<style lang="less">
#myNote {
    .tab-wrapper {
        position: relative;
        z-index: 0;
        width: 30%;
        .mu-tabs {
            border-bottom: 1px solid #ccc;
            background: #fff;
            .mu-tab-text {
                color: #666
            }
        }
    }
    .search-wrapper {
        float: right;
        z-index: -1;
        position: relative
    }
    .course-list-content {
        clear: both;
    }
    .row {
        margin-bottom: 20px;
    }
    // @main-color: #009688;
    .course-list-left {
        position: relative;
        width: 150px;
        height: 100px;
        cursor: pointer;
        img {
            width: 100%;
            height: 100%;
        }
        .total-time {
            position: absolute;
            line-height: 1;
            padding: 0;
            margin: 0;
            right: 5px;
            bottom: 5px;
            color: #fff;
        }
        .current-time {
            position: absolute;
            padding: 2px;
            margin: 0;
            background: rgba(0, 0, 0, 0.7);
            color: #fff;
            bottom: 0;
            width: 100%;
            text-align: center;
        }
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
                pageNum: 1,
                type: 1,
                activeTab: 'tab1'
            }
        },
        created() {
            this.viewCourse(this.pageNum)

        },
        methods: {
            handleTabChange(val) {
                this.activeTab = val
            },
            handleActive(type) {
                this.type = type;
                this.viewCourse(1);
            },
            pageClick(newIndex) {
                this.viewCourse(newIndex)
            },
            viewCourse(current) {
                const _self = this
                this.$http.get('/discuss/my/' + this.type, {
                    params: {
                        pageNum: current,
                        pageSize: 20,
                    }
                }).then((response) => {
                    response = response.body
                        // console.log(response)
                    if (response.failure.length === 0) {
                        console.log(response.success)
                        this.courses = response.success
                            // this.pageTotal = response.success.total
                    } else {
                        alert(response.failure[0])
                    }

                })
            },
            toDetail(info){
                console.log(info)
                let time = 0;

                // 视频
                if(info.lesson.material.type === 0){
                    const time_arr = info.point_time.split(':');
                    time = parseInt(time_arr[0]) * 60 + parseInt(time_arr[1]);
                    window.location.href = '/module/player.html#/video/'+info.lesson.id+'?point='+time+'&cid='+this.info.lesson.Course.id
                // 音频
                }else if(info.lesson.material.type === 1){
                    const time_arr = info.point_time.split(':');
                    time = parseInt(time_arr[0]) * 60 + parseInt(time_arr[1]);
                    window.location.href = '/module/player.html#/audio/'+info.lesson.id+'?point='+time+'&cid='+this.info.lesson.Course.id
                // PPT
                }else{
                    time = info.point_time
                    window.location.href = '/module/player.html#/page/'+info.lesson.id+'?point='+time+'&cid='+this.info.lesson.Course.id
                }
            }
        }
}
</script>
