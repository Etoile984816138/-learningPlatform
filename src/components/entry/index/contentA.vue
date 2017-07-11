<template>
    <mu-flexbox class="mt4">
        <mu-flexbox-item class="sidebar">
            <mu-list>
                <div class="mu-list-wrapper" v-for="direction in setDirection" :data-id="direction.d_id" :data-direction="direction.name" @mouseenter="showDetail" @mouseleave="hideDetail">
                    <mu-list-item :title="direction.name">
                    </mu-list-item>
                </div>
            </mu-list>
            <div class="sidebar-submenu" id="sidebar-submenu" @mouseenter="overFlag" @mouseleave="outFlag" v-show="detailShow^mouseoverFlag">
                <p class="title">{{direction}}</p>
                <span class="content" v-for="(item,index) in generics"><span class="name">{{item.name}}</span><span class="divide">|</span></span>

               
            </div>
        </mu-flexbox-item>
        <mu-flexbox-item grow="5" class="carousel">
            <!-- swiper -->
            <swiper :options="swiperOption" style="height:350px">
                <swiper-slide><img src="./banner1.jpg"></swiper-slide>
                <swiper-slide><img src="./banner2.jpg"></swiper-slide>
                <swiper-slide><img src="./banner3.jpg"></swiper-slide>
                <div class="swiper-pagination" slot="pagination"></div>
            </swiper>
        </mu-flexbox-item>
    </mu-flexbox>
</template>
<style lang='less'>
@main-color: #009688;
.mu-list {
    background: @main-color;
    .mu-item-title {
        text-align: left;
        color: #fff;
    }
}
.divide{
    display: inline-block;
    padding: 0 20px;
}
.sidebar {
    position: relative;
}
// .carousel{
//     position: relative;
//     z-index: 0;
// }
.sidebar-submenu {
    position: absolute;
    top: 0;
    left: 180px;
    z-index: 2;
    padding: 10px;
    width: 500px;
    min-height: 350px;
    background: #fff;
    .title {
        padding: 0 5px;
        text-align: left;
        font-size: 16px;
        color: @main-color;
        border-left: 5px solid @main-color;
        cursor: pointer;
        &:hover {
            color: #ccc;
        }
    }
    .content {
        color: #777;
        text-align: left;
        padding: 0;
        // margin-right: 10px;
        cursor: pointer;
        line-height: 2;
        .name:hover {
            color: #ccc;
        }
    }
}

.swiper-pagination-bullet-active {
    background: #009688;
}

.mt8 {
    margin-top: 8px;
}
</style>
<script>
const ERR_OK = 0
export default {
    props: {
        directions: {
            type: Array
        }
    },
    data() {
        return {
            swiperOption: {
                pagination: '.swiper-pagination',
                nextButton: '.swiper-button-next',
                prevButton: '.swiper-button-prev',
                paginationClickable: true,
                spaceBetween: 30,
                centeredSlides: true,
                autoplay: 2500,
                autoplayDisableOnInteraction: false
            },
            detailShow: false,
            mouseoverFlag: false,
            direction_id: '',
            direction:'',
            generics:[]
        }
    },
    created() {
    },
    methods: {
        showDetail(e) {
            this.direction_id = e.currentTarget.dataset.id;
            this.direction = e.currentTarget.dataset.direction
            this.$http.get('/direction/' + this.direction_id).then((response) => {
                response = response.body
                    if (response.failure.length === 0) {
                        this.generics = response.success
                    } else {
                        alert(response.failure[0])
                    }
                
            })
            this.detailShow = true
        },
        hideDetail() {
            this.detailShow = false
        },
        overFlag() {
            this.mouseoverFlag = true
        },
        outFlag() {
            this.mouseoverFlag = false
        }
    },
    computed:{
        setDirection:function(){
            let arr = this.directions;
            if(arr.length < 7){
                for(let i=arr.length;i<7;i++){
                    arr.push({
                        d_id:0,
                        name:''
                    })
                }
            }else if(arr.length > 7){
                arr = arr.slice(0,7)
            }
            return arr;
        }
    }

}
</script>
