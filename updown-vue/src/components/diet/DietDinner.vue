<template>
    <div class="container">
        <KeepAlive>
            <component :is="choose" :category="'저녁'" :info="info" @regist="regist" @modify="modify" @getInfo="getInfo" @search="search" :diet="diet" @home="home"  @remove="remove" />
        </KeepAlive>
    </div>
</template>

<script setup>
import { shallowRef, ref } from 'vue'
import DietDetail from '@/components/diet/DietDetail.vue';
import DietModify from './DietModify.vue';
import DietUpload from './DietUpload.vue';
import DietSearch from './DietSearch.vue';
import { useDietStore } from '@/stores/diet';

const store = useDietStore();
let choose = shallowRef(DietDetail);

let diet = ref({});

const modify = function(d) {
    diet.value=d;
    choose.value = DietModify;
}
let info = ref({});

const getInfo = function(i) {
    info.value = i;
    console.log(info.value)
    choose.value = DietUpload;
} 

const regist = function() {
    choose.value = DietUpload;
}

const home = function() {
    choose.value = DietDetail;
}

const search = function(s) {
    choose.value = DietSearch;
}  

const remove = function(diet) {
    store.remove(diet);
}

</script>


<style scoped>
.container {
    display: inline-block;
    width: 35rem;
    height: 20rem;
    border-radius: 0.5rem;
    background-color: white;
    margin: 1em 1em 0 0;
    padding: 0.7em;
}

</style>