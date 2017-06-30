<template>
  <div class="right-sider">
    <div class="right-sider-box">
      在线客服
    </div>
    <div class="right-sider-box">
      意见反馈
    </div>
    <div class="right-sider-box" @mouseenter="showDetail" @mouseout="hideDetail">
      手机课堂
    </div>
    <div class="qr-wrapper" v-show="detailShow">
      <img src="qr.png" class="qr">
    </div>
  </div>
</template>
<script>
export default {
    data() {
        return {
            detailShow: false
        }
    },
    methods: {
        showDetail() {
            this.detailShow = true
        },
        hideDetail() {
            this.detailShow = false
        }
    }

}
</script>
<style lang="less">
  @main-color: #009688;
  .right-sider{
    position: fixed;
    right: 10px;
    top: 102px;
    background: #fff;
    border: 1px solid #ccc;
    border-top: none; 
  }
  .right-sider-box{
    width: 60px;
    height: 60px;
    padding: 10px;
    border-top: 1px solid #ccc;
    text-align: center;
    &:last-child{
      border-bottom: 1px solid #ccc;
    }
    &:hover{
      background: @main-color;
      color: #fff;
      border: 1px solid @main-color;
      cursor: pointer;
    }

  }
  .qr-wrapper{
    position: absolute;
    bottom: 0;
    right: 60px;
    width: 100px;
    height: 100px;
    border: 1px solid #ccc;
    background: #fff;
    img{
      width: 100%;
      height: 100%;
    }
  }
</style>

