<!-- 
    逻辑概述：1.点击all-tag，其它tag上active去掉
            2.点击tag，all-tag上active去掉
 -->
<template>
    <div id="courseHeader" class="course-header">
        <mu-row>
            <mu-col width="100" tablet="20" desktop="20">
                <div class="cover-wrapper">
                    <img :src="courseData.cover">
                </div>
            </mu-col>
            <mu-col width="100" tablet="80" desktop="80">
                <mu-list-item disabled>
                    <h1>{{courseData.title}}</h1>
                    <div @click="setBadgeText">
                        <mu-badge :content="badgeText" slot="after" :class="{'badge-active':isCollect}" />
                    </div>
                    <p>{{student_num}}人在学
                        <span class="star-wrapper">
                        评分：
                        <mu-icon-button icon="grade" v-for="item in courseData.star" :key="courseData.id"/>
                        <mu-icon-button icon="star_border" v-for="item in star_num" :key="courseData.id"/>
                    </span>
                    </p>
                    <div v-if="isJoin">
                        <mu-linear-progress mode="determinate" :value="courseData.hasStudy" class="progress" :size="progressSize" :max="courseData.total" />
                        <p>已学习{{courseData.hasStudy}}个课时&nbsp;&nbsp;&nbsp;共{{courseData.total}}个课时</p>
                    </div>
                    <div v-else>
                        <mu-raised-button label="立即参加" class="demo-raised-button" @click="joinCourse" primary/>
                    </div>
                </mu-list-item>
            </mu-col>
        </mu-row>
    </div>
</template>
<script>
const ERR_OK = 0;
export default {
    // props: { }, data() { }, methods: { }, computed: { }
    data() {
            return {
                test: '',
                value: 20,
                badgeText: '收藏',
                progressSize: 10,
                isCollect: false
            }
        },
        props: {
            courseData: {
                type: Object
            },
            bus: {
                type: Object
            },
            isJoin:{
                type:Boolean
            }
        },
        created() {
            try {
                if (this.courseData.hasCollect) {
                    this.badgeText = '已收藏'
                    this.isCollect = true
                } else {
                    this.badgeText = '收藏'
                    this.isCollect = false
                }
            } catch (e) {
                console.log(e)
            }

        },
        methods: {
            joinCourse() {
                this.$http.get('/course/add/' + this.courseData.id).then((response) => {
                    response = response.body
                        // console.log(response)
                    if (response.failure.length === 0) {
                        this.bus.$emit("alertFlag", {
                            flag: true,
                            text: '加入成功'
                        });
                        // console.log(this.chapter)
                    } else {
                        this.bus.$emit("alertFlag", {
                            flag: true,
                            text: response.failure[0]
                        });
                    }

                })
            },
            setBadgeText() {
                this.$http.post('/collection/' + this.courseData.id).then((response) => {
                    response = response.body
                        // console.log(response)
                    if (response.failure.length === 0) {
                        if (this.isCollect) {
                            this.badgeText = '收藏'
                            this.isCollect = false
                        } else {
                            this.badgeText = '已收藏'
                            this.isCollect = true
                        }
                    } else {
                        alert(response.failure[0])
                    }

                });

            }
        },
        computed: {
            student_num() {
                console.log('-------------------')
                console.log(this.courseData)
                const _self = this;
                try {
                    if(_self.courseData.employees != null){
                        return _self.courseData.employees.length
                    }else{
                        return 0
                    }
                } catch (e) {
                    console.log(e)

                }
                // while (!this.courseData);
                // return this.courseData.students.length
            },
            star_num() {
                if (isNaN(this.courseData.star)) {
                    return 0;
                } else {
                    return (5 - this.courseData.star);
                }
            }
        }



}
</script>
<style lang="less">
@main-color: #009688;
#courseHeader {
    border-bottom: 1px solid #ccc;
    padding-bottom: 40px;
    margin-bottom: 40px;
    .cover-wrapper {
        // margin:50px;
        width: 100%;
        height: 250px;
        border: 1px solid #ccc;
        img {
            width: 100%;
            height: 100%;
        }
    }
    .star-wrapper {
        display: inline-block;
        margin-left: 100px;
        .mu-icon-button {
            position: relative;
            top: 5px;
            width: 16px;
            height: 16px;
            .mu-icon {
                font-size: 24px;
                color: #ffc107
            }
        }
    }
    .f-btn-group {
        display: flex;
        justify-content: space-between;
        align-items: flex-end;
        margin-top: 40px;
        .mu-icon-button {
            width: 8px;
            height: 8px;
            clear: both;
            .mu-icon {
                font-size: 20px;
                color: #000;
                &:hover {
                    color: @main-color;
                }
            }
        }
    }
    .progress {
        width: 100%;
    }
    .badge-active {
        cursor: pointer;
        .mu-badge {
            background: #fdd835;
        }
        // color: #ffeb3b;
    }
    .mu-badge {
        cursor: pointer;
    }
}
</style>
