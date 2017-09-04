<template>
  <div id="app">
    <v-header></v-header>
    <v-rightSider></v-rightSider>
    <div id="content" class="content">
      <v-courseHeader :courseData="courseData" :bus="bus" :isJoin="isJoin"></v-courseHeader>
      <mu-row gutter>
        <mu-col width="100" tablet="70" desktop="70" class="tab-detail">
          <mu-row>
            <mu-col width="100" tablet="50" desktop="50">
              <div class="tab">
                <mu-tabs :value="activeTab" @change="handleTabChange">
                  <mu-tab value="introduction" title="介绍" />
                  <mu-tab value="chapter" title="章节" />
                  <mu-tab value="discussion" title="讨论区" />
                </mu-tabs>
              </div>
            </mu-col>
          </mu-row>
          <div>
            <!-- 课程介绍 -->
            <div class="introduction tab-page" :class="{active: activeTab === 'introduction'}">
              <p>虚拟现实技术是仿真技术的一个重要方向，是仿真技术与计算机图形学人机接口技术多媒体技术传感技术网络技术等多种技术的集合，是一门富有挑战性的交叉技术前沿学科和研究领域。虚拟现实技术(VR)主要包括模拟环境、感知、自然技能和传感设备等方面。模拟环境是由计算机生成的、实时动态的三维立体逼真图像。感知是指理想的VR应该具有一切人所具有的感知。除计算机图形技术所生成的视觉感知外，还有听觉、触觉、力觉、运动等感知，甚至还包括嗅觉和味觉等，也称为多感知。自然技能是指人的头部转动，眼睛、手势、或其他人体行为动作，由计算机来处理与参与者的动作相适应的数据，并对用户的输入作出实时响应，并分别反馈到用户的五官。传感设备是指三维交互设备。</p>
              <p>VR艺术是伴随着“虚拟现实时代”的来临应运而生的一种新兴而独立的艺术门类，在《虚拟现实艺术： 形而上的终极再创造》一文中，关于VR艺术有如下的定义：“以虚拟现实（VR）、增强现实（AR）等人工智能技术作为媒介手段加以运用的艺术形式，我们称之为虚拟现实艺术，简称VR艺术。该艺术形式的主要特点是超文本性和交互性。” “作为现代科技前沿的综合体现，VR艺术是通过人机界面对复杂数据进行可视化操作与交互的一种新的艺术语言形式，它吸引艺术家的重要之处，在于艺术思维与科技工具的密切交融和二者深层渗透所产生的全新的认知体验。与传统视窗操作下的新媒体艺术相比，交互性和扩展的人机对话，是VR艺术呈现其独特优势的关键所在。从整体意义上说，VR艺术是以新型人机对话为基础的交互性的艺术形式，其最大优势在于建构作品与参与者的对话，通过对话揭示意义生成的过程。 艺术家通过对VR、AR等技术的应用，可以采用更为自然的人机交互手段控制作品的形式，塑造出更具沉浸感的艺术环境和现实情况下不能实现的梦想，并赋予创造的过程以新的含义。如具有VR性质的交互装置系统可以设置观众穿越多重感官的交互通道以及穿越装置的过程，艺术家可以借助软件和硬件的顺畅配合来促进参与者与作品之间的沟通与反馈，创造良好的参与性和可操控性；也可以通过视频界面进行动作捕捉，储存访问者的行为片段，以保持参与者的意识增强性为基础，同步放映增强效果和重新塑造、处理过的影像；通过增强现实、混合现实等形式，将数字世界和真实世界结合在一起，观众可以通过自身动作控制投影的文本，如数据手套可以提供力的反馈，可移动的场景、360度旋转的球体空间不仅增强了作品的沉浸感，而且可以使观众进入作品的内部，操纵它、观察它的过程，甚至赋予观众参与再创造的机会。”
              </p>
            </div>
            <!-- 课程章节 -->
            <div class="chapter tab-page" :class="{active: activeTab === 'chapter'}">
              <mu-list>
                <mu-list-item v-for="(section, sectionIndex) in chapter.chapters" :title="'第'+(sectionIndex+1)+'章：'+section.title" :key="section.id" toggleNested>
                  <mu-list-item v-for="(chapter,chapterIndex) in section.lessons" :title="'第'+(chapterIndex+1)+'课时：'+chapter.title" :key="chapter.id" slot="nested" @click="choiseLessons(chapter.material.type,chapter.id)">
                    <mu-list-item slot="left" disabled v-if="chapter.material.type === 0">
                      <mu-icon value="live_tv" />
                    </mu-list-item>
                    <mu-list-item slot="left" disabled v-else-if="chapter.material.type === 1">
                      <mu-icon value="settings_voice" />
                    </mu-list-item>
                    <mu-list-item slot="left" disabled v-else>
                      <mu-icon value="filter_none" />
                    </mu-list-item>
                    <mu-list-item slot="right" class="chapter-states" disabled>
                      <span :class="{active: chapter.noted}">划重点</span>
                      <span :class="{active: chapter.question}">有疑问</span>
                      <span :class="{active: chapter.complete}">已完成</span>
                      <div v-if="chapter.material.type === 2">
                        <span>{{chapter.material.page}}页</span>
                      </div>
                      <div v-else>
                        <span>{{chapter.material.length}}</span>
                      </div>
                    </mu-list-item>
                  </mu-list-item>
                </mu-list-item>
              </mu-list>
            </div>
            <!-- 讨论区 -->
            <div class="chapter tab-page" :class="{active: activeTab === 'discussion'}">
              <mu-sub-header>欢迎进入课程讨论区，你可以与本课程的老师和同学在这里交流。如果你有课程相关的问题，请发到老师答疑区；经验、思考、创意、作品、转帖请发到综合讨论区。欢迎分享，鼓励原创，杜绝广告，请大家共同维护一个包容、积极、相互支持的交流氛围，谢谢。
              </mu-sub-header>
              <mu-raised-button label="发起主题" class="add-discussion" secondary @click="open" />
              <mu-dialog :open="dialog" title="发起主题" @close="close">
                <mu-select-field v-model="addDiscuss.type" label="*所选讨论区模块" @change="test">
                  <mu-menu-item value="1" title="老师答疑区" />
                  <mu-menu-item value="2" title="综合讨论区" />
                </mu-select-field>
                <div></div>
                <mu-select-field v-model="s_index" label="选择章节" hintText="请选择章节">
                  <mu-menu-item v-for="(course,index) in chapter.chapters" :key="course.id" :value="index" :title="course.title">
                  </mu-menu-item>
                </mu-select-field>
                <mu-select-field v-model="addDiscuss.lesson_id" label="选择课时" hintText="请选择课时" v-if="s_index">
                  <mu-menu-item v-for="chapterItem in chapter.chapters[s_index].lessons" :key="chapterItem.id" :value="chapterItem.id" :title="chapterItem.title">
                  </mu-menu-item>
                </mu-select-field>
                <mu-text-field hintText="请输入标题" fullWidth label="标题" v-model="addDiscuss.title" />
                <mu-text-field hintText="请输入内容" fullWidth label="主题内容" v-model="addDiscuss.content" multiLine/>
                <mu-switch label="匿名" class="demo-switch" v-model="addDiscuss.isNick" />
                <mu-flat-button slot="actions" @click="dialogClose" primary label="取消" />
                <mu-raised-button slot="actions" primary @click="addDiscussF" label="确定" />
              </mu-dialog>
              <mu-list>
                <mu-list-item disabled>
                  <mu-list-item class="sub-list-title" @click="setDiscussionTitle('老师答疑区',1)">老师答疑区</mu-list-item>
                  <mu-list-item class="sub-list-title" @click="setDiscussionTitle('综合讨论区',2)">综合讨论区</mu-list-item>
                  </mu-list-item》>
              </mu-list>
              <mu-sub-header>{{discussionAreaTitle}}</mu-sub-header>
              <mu-list>
                <mu-list-item :title="discu.employee.name" disabled v-for="(discu,index) in discusionData" :key="discu.id">
                  <mu-avatar slot="left" :src="discu.employee.port" />
                  <span slot="describe">
                                        <mu-badge content="教师" secondary slot="right" v-if="discu.employee.identity == 2"/>
                                        <mu-badge content="管理员" secondary slot="right" v-else-if="discu.employee.identity == 0"/>
                                        <span>{{discu.time}}</span>
                  </span>
                  <p @click="setClose(discu, index) " class="hover-state">{{discu.title}}</p>
                  <transition name="slide-fade">
                    <mu-list-item v-show="discu.close">{{discu.content}}</mu-list-item>
                  </transition>
                  <span style="float:right">
                                    <span v-if="discu.commends != null" @click="setReply(discu,index)" class="hover-state">回复（{{discu.commends.length}}）  </span>
                  <span @click="setReply(discu,index)" class="hover-state" v-else>回复</span>
                  <span class="hover-state" @click="setSupport(index)">点赞（{{discu.grate}}）</span>
                  </span>
                  <transition name="slide-fade">
                    <div v-show="discu.active">
                      <mu-list>
                        <mu-list-item v-for="(reply,replyIndex) in discu.commends" :key="reply.from.id" :title="reply.from.name">
                          <mu-avatar slot="left" :src="reply.from.port" />
                          <span slot="describe">
                                                    </span>
                          <p>{{reply.content}}</p>
                          <span style="float:right">
                                                    <span class="hover-state" @click="setReplySupport(index,replyIndex)">点赞（{{reply.grate}}）</span>
                          </span>
                        </mu-list-item>
                      </mu-list>
                      <mu-text-field hintText="请输入文字" label="回复" v-model="replyText" fullWidth labelFloat/>
                      <mu-raised-button label="回复" class="reply-btn" primary style="float:right" @click="reply(index)" />
                    </div>
                  </transition>
                </mu-list-item>
              </mu-list>
              <mu-pagination :total="pageTotal" :current="pageCurrent" @pageChange="handleClick">
              </mu-pagination>
            </div>
          </div>
        </mu-col>
        <mu-col width="100" tablet="30" desktop="30">
          <div v-if="isJoin">
            <div class="notice-list">
              <mu-list>
                <mu-sub-header>公告</mu-sub-header>
                <mu-list-item><p>VR设计顶尖开发者带你了解VR前世今生，感受VR酷炫效果。从了解虚拟现实游戏设计再到Unity VR游戏设计开发，带你领略VR游戏的魅力，让你玩得专业，学得精彩！</p></mu-list-item>
              </mu-list>
            </div>
          </div>
          <div class="comment-list">
            <mu-list>
              <mu-sub-header>评价</mu-sub-header>
              <div>
                <mu-list-item disabled class="set-star" v-if="isJoin && !isScore">
                  <span style="position:relative;top:-5px">打分：</span>
                  <mu-icon-button icon="star" v-for="item in setScore" @click="setScoreFull(item)" :tooltip="comment_tooltip[item]" :key="item" />
                  <mu-icon-button icon="star_border" v-for="item in (5-setScore)" @click="setScoreBorder(item)" :tooltip="comment_tooltip[setScore+item-1]" :key="item" />
                  <div></div>
                  <mu-text-field hintText="请输入评论内容" multiLine :rows="3" :rowsMax="6" fullWidth/>
                  <div></div>
                  <mu-raised-button label="确定" class="send-score" primary style="float:right" @click="sendScore" />
                </mu-list-item>
                <mu-list-item disabled v-if="isJoin && isScore">
                  我的评分：
                  <mu-icon value="grade" v-for="item in myScore" :key="item" />
                  <mu-icon value="star_border" v-for="item in (5-myScore)" :key="item" />
                </mu-list-item>
              </div>
              <mu-list-item :title="comment.name" v-for="comment in courseData.courseComments" :key="comment.id">
                <mu-avatar :src="comment.port" slot="leftAvatar" />
                <mu-icon value="grade" v-for="item in comment.rate" :key="item" />
                <mu-icon value="star_border" v-for="item in (5-comment.rate)" :key="item" />
                <mu-list-item slot="right" class="time" disabled>
                  {{comment.time}}
                </mu-list-item>
              </mu-list-item>
            </mu-list>
            <span class="getMore">详情>></span>
          </div>
          <div class="learner-list">
            <mu-list>
              <mu-sub-header>{{student_num}}人在学</mu-sub-header>
              <mu-list-item class="learner-avatar" v-for="student in courseData.employees" :key="student.id">
                <mu-avatar slot="left" :src="student.port" />
              </mu-list-item>
            </mu-list>
            <span class="getMore">详情>></span>
          </div>
        </mu-col>
      </mu-row>
      <mu-snackbar v-if="snackbar" :message="alertText" @actionClick="hideSnackbar" @close="hideSnackbar" style="text-align:center" />
    </div>
    <v-footer></v-footer>
  </div>
</template>
<script>
import header from 'components/common/header/header.vue'
import footer from 'components/common/footer/footer.vue'
import rightSider from 'components/common/rightSider/rightSider.vue'

import courseHeader from 'entry/courseDetail/courseHeader.vue'


const ERR_OK = 0
import Vue from 'vue'
export default {
  data() {
    return {
      courseData: {},
      activeTab: 'introduction',
      chapter: {},
      bus: new Vue(),
      snackbar: false,
      alertText: '未知错误',
      discussionAreaTitle: '老师答疑区',
      discussiontype: 1,
      discusionData: [],
      close: false,
      toggleReply: false,
      to: '',
      replyText: '',
      user: {},
      dialog: false,
      s_index: null,
      addDiscuss: {
        content: "",
        type: '1',
        lesson_id: 1,
        title: "",
        isNick: false
      },
      pageTotal: 100,
      pageCurrent: 1,
      setScore: 0,
      comment_tooltip: ['十分不满意', '不满意', '中等', '满意', '非常满意'],
      isScore: false,
      myScore: 0,
      isJoin: false
    }
  },
  components: {
    'v-header': header,
    'v-footer': footer,
    'v-rightSider': rightSider,
    'v-courseHeader': courseHeader
  },
  created() {
    this.setDiscussion();
    this.$http.get('/course/' + this.cid).then((response) => {
      response = response.body
      // console.log(response)
      if (response.failure.length === 0) {
        this.courseData = response.success
      } else {
        alert(response.failure[0])
      }

    });
    this.$http.get('/course/' + this.cid + '/section').then((response) => {
      response = response.body

      if (response.failure.length === 0) {
        this.chapter = response.success
        this.isJoin = this.chapter.hasjoin
        console.log(this.chapter)
      } else {
        alert(response.failure[0])
      }

    });

    if (this.bus) {
      const _self = this;
      this.bus.$on("alertFlag", function(msg) {
        console.log("接收到其他组件传递的数据：");
        console.log(msg)
        _self.snackbar = msg.flag;
        _self.alertText = msg.text;
        _self.isJoin = true;
        _self.showSnackbar();
        // location.reload();
      });
    };
    this.$http.get('/user').then((response) => {
      response = response.body
      // console.log(response)
      if (response.failure.length === 0) {
        this.user = response.success

      } else {
        alert(response.failure[0])
      }

    });


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
    handleTabChange(val) {
      this.activeTab = val
    },
    handleActive() {
      window.alert('tab active')
    },
    showSnackbar() {
      this.snackbar = true
      if (this.snackTimer) clearTimeout(this.snackTimer)
      this.snackTimer = setTimeout(() => {
        this.snackbar = false
      }, 1000)
    },
    hideSnackbar() {
      this.snackbar = false
      if (this.snackTimer) clearTimeout(this.snackTimer)
    },
    setDiscussionTitle(val, type) {
      this.discussionAreaTitle = val;
      this.discussiontype = type;
      this.pageCurrent = 1;
      this.setDiscussion();
    },
    setDiscussion() {
      const _self = this;
      this.$http.get('/discuss/' + this.cid + '/course/' + this.discussiontype, {
        params: {
          "pageNum": _self.pageCurrent,
          "pageSize": 10
        }
      }).then((response) => {
        response = response.body
        if (response.failure.length === 0) {
          _self.discusionData = response.success;
          _self.pageTotal = response.total;
        } else {
          alert(response.failure[0])
        }

      })
    },
    setClose(item, index) {
      // alert(item)
      // alert(index)
      if (typeof item.close == 'undefined' || item.close == false) {
        this.$nextTick(function() {　　　　　　　　
          this.discusionData.forEach(function(item) {
            Vue.set(item, 'close', false);　　　　　　　　
          });　　　　　
          Vue.set(item, 'close', true);　　
        });
      } else {
        item.close = false
      }
    },
    setReply(item, index) {
      if (typeof item.active == 'undefined' || item.active == false) {
        this.$nextTick(function() {　　　　　　　　
          this.discusionData.forEach(function(item) {
            Vue.set(item, 'active', false);　　　　　　　　
          });　　　　　
          Vue.set(item, 'active', true);　　
          this.to = item.employee.name;　
          this.replyText = '回复@' + item.employee.name + '：'
        });
      } else {
        item.active = false
      }
    },
    setSubReply(item) {
      this.to = item.to.name;
      this.replyText = '回复@' + item.to.name + '：'
    },
    setSupport(index) {
      this.discusionData[index].grate++;
    },
    setReplySupport(index, replyIndex) {
      this.discusionData[index].commends[replyIndex].grate++;
    },
    reply(index) {
      const _self = this;
      const today = new Date();
      console.log(_self.replyText)
      if (!this.discusionData[index].commends) {
        this.discusionData[index].commends = []
      }
      this.discusionData[index].commends.push({
        id: 1,
        to: {
          id: 0,
          name: _self.to,
          isTeacher: false
        },
        time: today.getFullYear() + '-' + (today.getMonth() + 1) + '-' + (today.getDate()),
        from: {
          id: 0,
          name: _self.user.name,
          port: _self.user.portrait,
          isTeacher: false
        },
        content: _self.replyText,
        grate: 0
      })
      this.replyText = ''
    },
    open() {
      this.dialog = true
    },
    dialogClose() {
      this.dialog = false
    },
    addDiscussF() {
      const _self = this;
      console.log('-------')
      console.log(this.addDiscuss)
      const params = this.addDiscuss
      console.log(params)
      this.$http.post('/discuss/publish', params).then((response) => {
        response = response.body
        // if (response.failure.length === 0) {
        _self.dialog = false
        this.alertText = '发帖成功！'
        this.showSnackbar();
        this.setDiscussion();
        // } else {
        //     alert(response.failure[0])
        // }

      })

    },
    test() {
      console.log(this.addDiscuss)
      console.log(this.s_index)
      console.log(this.addDiscuss.lesson_id)
    },
    handleClick(newIndex) {
      this.pageCurrent = newIndex;
      this.setDiscussion();
    },
    setScoreFull(item) {
      this.setScore = item;
      console.log(this.setScore)
    },
    setScoreBorder(item) {
      this.setScore += item;
      console.log(this.setScore)
    },
    sendScore() {
      this.isScore = true;
      this.myScore = this.setScore;
    },
    choiseLessons(type, id) {
      console.log(type)
      console.log(id)
      // console.log(window.location)
      if (type === 2) {

        window.location.href = '/module/player.html#page/' + id + '?cid=' + this.cid
      } else if (type === 1) {
        window.location.href = '/module/player.html#audio/' + id + '?cid=' + this.cid
      } else {
        window.location.href = '/module/player.html#video/' + id + '?cid=' + this.cid
      }
    }

  },
  computed: {
    cid: function() {

      if (typeof this.handleUrl()['cid'] == 'undefined') {
        return 0;
      } else {
        return this.handleUrl()['cid']
      }

    },
    student_num() {
      try {
        return this.courseData.employees.length
      } catch (e) {
        console.log('数据读取中')

      }
    }
  },
  mounted() {

  }
}

</script>
<style lang="less">
@main-color: #009688;
.content {
    padding: 50px 100px;
    margin-top: 50px;
    .tab {
        position: relative;
        z-index: 0
    }
    .tab-detail {
        border: 1px solid #ccc;
        padding: 15px;
        // height: 500px;
    }
}

.comment-list {
    border: 1px solid #ccc;
    .time {
        .mu-item {
            display: inline-block;
            padding-right: 80px;
            font-size: 12px;
            color: #ccc;
        }
    }
    .mu-icon {
        font-size: 16px;
        color: #ffc107
    }
    .mu-icon-button {
        // position: relative;
        // top:5px;
        width: 24px;
        height: 24px;
        .mu-icon {
            font-size: 24px;
        }
    }
}

.learner-list {
    margin-top: 15px;
    border: 1px solid #ccc;
    .learner-avatar {
        float: left;
    }
}

.notice-list {
    margin-bottom: 15px;
    border: 1px solid #ccc;
    line-height: 3;
    letter-spacing: 2px;
}

.tab-page {
    display: none;
}

.tab-page.active {
    display: block;
}

.sub-list-title {
    border-bottom: 1px solid #ccc
}

.chapter {
    .add-discussion {
        margin-left: 18px;
        margin-top: 20px;
        margin-bottom: 20px;
    }
}

.hover-state {
    &:hover {
        color: @main-color;
        cursor: pointer;
    }
}

.slide-fade-enter-active {
    transition: all .5s ease;
}

.slide-fade-leave-active {
    transition: all .5s ease;
    // transition: all .5s cubic-bezier(1.0, 0.5, 0.8, 1.0);
}

.slide-fade-enter,
.slide-fade-leave-active {
    transform: translateX(10px);
    opacity: 0;
}

.mu-pagination {
    display: flex;
    justify-content: center
}

.chapter-states {
    .mu-item-content {
        display: flex;
    }
    padding-right: 200px;
    span {
        display: inline-block;
        margin-left: 10px;
        white-space: nowrap;
        &.active {
            color: @main-color
        }
    }
}

.getMore {
    display: flex;
    justify-content: flex-end;
    padding: 10px;
    color: #ccc;
    cursor: pointer;
    &:hover {
        color: @main-color
    }
}

.introduction p{
    line-height: 3;
    letter-spacing: 2px;
    text-indent: 2em;
}
</style>
