<template>
    <div id="myMsn">
        <div class="tab-wrapper">
            <mu-tabs :value="activeTab" @change="handleTabChange">
                <mu-tab value="comment" title="讨论区通知" @active="handleActive('comment')" />
                <mu-tab value="friend" title="好友通知" @active="handleActive('friend')" />
                <mu-tab value="system" title="系统通知" @active="handleActive('system')" />
            </mu-tabs>
        </div>
        <mu-raised-button label="全部标记为已读" class="demo-raised-button"/>
        <mu-list>
            <mu-list-item v-for="(item,index) in msg" :title="'来自'+item.from.name+'的消息['+item.content+']'" class="new-tip"  :key="index">
                <mu-list-item :title="item.time" slot="right" disabled class="time"></mu-list-item>
                <mu-badge content="" class="icon-badge" circle secondary color="red" v-if="item.isRead">
                </mu-badge>
            </mu-list-item>
        </mu-list>
        <mu-pagination :total="pageTotal*10" :current="pageNum" @pageChange="pageClick">
        </mu-pagination>
    </div>
</template>
<script>
export default {

}
</script>
<style lang="less">
#myMsn {
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
    .new-tip {
    	position: relative;
        border-bottom: 1px solid #ccc;
        .icon-badge{
        	position: absolute;
        	left:10px;
        	top:10px;
        }
        .mu-badge-circle{
        	width: 5px;
        	height: 5px;
        }
        .mu-item-left {
            width: 10px;
        }
        .time {
            white-space: nowrap;
            .mu-item-title {
                color: #ccc;
            }
        }
    }
    .demo-raised-button{
        margin:20px 0;
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
                activeTab: 'comment',
                size: 1,
                type:'comment',
                msg:[]
            }
        },
        created() {
            this.viewCourse(this.pageNum)
            // console.log(this.msg)

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
                this.$http.get('/news/'+_self.type, {
                    params: {
                        pageNum: current,
                        pageSize: 12,
                    }
                }).then((response) => {
                    response = response.body
                        // console.log(response)
                    if (response.failure.length === 0) {
                        console.log(response.success)
                        _self.msg = response.success
                    } else {
                        alert(response.failure[0])
                    }

                })
            }
        }
}
</script>
