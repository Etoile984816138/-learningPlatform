<template>
  <div id="app">
    <v-header></v-header>
    <v-rightSider></v-rightSider>
    <div id="content" class="content">
      <v-contentA :directions="directions"></v-contentA>
      <div class="content-title">
        <p>向你推荐</p>
      </div>
      <v-courseList :tabShow="tabShow" :coursesUrl="coursesUrl" :pagination="pagination" :pageShow="pageShow" :bus="bus"></v-courseList>
      <div class="loop-wrapper">
        <mu-flat-button label="换一换" class="demo-flat-button" icon="loop" primary @click="recommendByType"/>
        <!-- <mu-icon value="loop" class="loop" />
        <span @click="recommendByType">换一换</span> -->
      </div>
    </div>
    <v-footer></v-footer>
  </div>
</template>
<script>
import header from 'components/common/header/header.vue'
import footer from 'components/common/footer/footer.vue'
import rightSider from 'components/common/rightSider/rightSider.vue'

import contentA from 'entry/index/contentA.vue'
import courseList from 'components/common/courseList/courseList.vue'
import Vue from 'vue'

export default {
  data() {
    return {
      tabShow: false,
      directions: [],
      coursesUrl: '/course/commendations/1',
      pagination: {
        pageNum: 1,
        pageSize: 16,
        pageTotal: 58
      },
      pageShow: false,
      bus: new Vue(),
      type:0


    }
  },
  mounted() {
    // this.init()
  },
  created() {
    this.$http.get('/direction').then((response) => {
      response = response.body
      // console.log(response)
      if (response.failure.length === 0) {
        console.log('success')
        this.directions = response.success
      } else {
        alert(response.failure[0])
      }

    })

  },
  components: {
    'v-header': header,
    'v-contentA': contentA,
    'v-courseList': courseList,
    'v-footer': footer,
    'v-rightSider': rightSider
  },
  watch: {

  },
  methods: {
    recommendByType: function() {
      this.type ++;
      const _self = this;
      this.bus.$emit("getType", {
        url: '/course/commendations/'+((_self.type%4)+1)
      });
    }
  }
}

</script>
<style lang="less">
@main-color: #009688;
.content {
    padding: 50px 100px;
    margin-top: 50px;
}

.content-title p {
    margin: 40px 0 20px 0;
    padding: 5px;
    border-left: 5px solid @main-color;
    font-size: 16px;
}
.loop-wrapper{
        color: @main-color;
        text-align: right;
        cursor: pointer;
        .mu-icon{
            vertical-align: middle;
        }
    }
</style>
