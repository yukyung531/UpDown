<template>
    <div class="start">
        <div id="img">
                <img src="../asset/image/표지이미지.png" style="height: 600px;">
        </div>
        <div id="show">
            <RouterView class="show" />
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
.show {
    padding-left: 160px;
    padding-top: 100px;
    /* width: 650px; */
    margin-left: 35px;
}

.start {
    display: flex;
    justify-content: center;
    /* height: 57rem; */
    height: 59.66rem;
    margin: 0;
}

#img{
    background-color: rgb(240, 240, 240);
    vertical-align: middle;
}
#show {
    display: flex;
    justify-content: left;
    padding-top: 80px;
    height: 931px;
    width: 750px;
}

#img:after{display:inline-block; height:100%; content:""; vertical-align:middle;}


img {
    width: 550px;
    /* height: 400px; */
    margin-left: 125px;
    margin-right: 125px;
    padding-top: 20px;
    padding-bottom: 30px;
    margin-bottom: 50px;

}
</style>