<template>
    <div class="signup-container">
        <form @submit.prevent="submitForm">
            <div>
                <p class="title">회원가입</p>
                <fieldset>
                    <div class="signup">
                        <label for="id">아이디</label>
                        <input v-model="signupUser.id" type="text" id="id" name="id" placeholder="아이디를 입력해주세요.">
                        <br>
                        <label for="password">비밀번호</label>
                        <input v-model="signupUser.password" autoComplete="off" type="password" id="password" name="password"
                            placeholder="비밀번호를 입력해주세요.">
                        <br>
                        <label for="password">비밀번호 확인</label>
                        <input v-model="password2" autoComplete="off" type="password" id="password2" name="password2"
                            placeholder="비밀번호를 입력해주세요.">
                        <br>
                        <label for="nickname">닉네임</label>
                        <input v-model="signupUser.nickname" type="text" id="nickname" name="nickname"
                            placeholder="닉네임을 입력해주세요.">
                        <br>
                        <label for="name">이름</label>
                        <input v-model="signupUser.name" type="text" id="name" name="name" placeholder="이름을 입력해주세요.">
                        <br>
                        <label for="email">이메일</label>
                        <input v-model="signupUser.email" type="text" id="email" name="email" placeholder="이메일을 입력해주세요.">
                        <br>
                        <label for="targetWeight">목표 체중</label>
                        <input v-model="signupUser.targetWeight" type="number" id="targetWeight" name="targetWeight"
                            placeholder="목표 체중을 입력해주세요.">
                                <a>
                                    kg
                                </a>
                        <label for="targetTime">하루 목표 운동 시간</label>
                        <input v-model="signupUser.targetTime" type="number" id="targetTime" name="targetTime"
                            placeholder="하루 목표 운동 시간을 입력해주세요.">
                        <a>
                            min
                        </a>
                        <label>프로필 사진(선택)</label>
                        <div class="input-group flex-nowrap" style="width: 400px;">
                            <input type="file" class="form-control" placeholder="Username" aria-label="Username" ref="serveyImage"
                                aria-describedby="addon-wrapping =" style="color: #a8a7a7; height: 35px;">
                        </div>
    
                        <br>
                        <input id="gosignup" type="submit" value="회원가입">
                        <div id="signup">
                            <RouterLink to="/">홈으로</RouterLink>
                        </div>
                    </div>
                </fieldset>
            </div>
        </form>

    </div>
</template>

<script setup>
import { ref } from 'vue';
import { useRouter } from 'vue-router';
import axios from 'axios';

const router = useRouter();


const serveyImage = ref(null);

// 비밀번호 확인용
const password2 = ref('')

const signupUser = ref({
    id: '',
    password: '',
    nickname: '',
    name: '',
    email: '',
    targetWeight: '',
    targetTime: '',
    updown: '',
    isSecret: '',
    img: '',
    orgImg: ''
})

// 전체 유저
const users = ref([]);

// 모든 유저 가져오는 메서드
const getUserList = () => {
    axios({
        url: `http://localhost:8080/updown/user`,
        method: "GET"
    })
        .then((res) => {
            users.value = Array.isArray(res.data) ? res.data : [];
        })
        .catch((err) => {
            console.log(err);
        })

}
// 이미지 업로드
const submitForm = async () => {
    if (serveyImage.value.files[0] == null) {
        goSignup();
        return;
    }

    const formData = new FormData();
    formData.append('file', serveyImage.value.files[0]);
    // 유저 정보
    // signupUser.value.img = serveyImage.value.files[0]

    axios({
        url: `http://localhost:8080/updown/img/regist`,
        method: 'POST',
        data: formData,
        headers: {
            'Content-Type': 'multipart/form-data'
        },
    }).then((res) => {
        signupUser.value.img = res.data.img
        signupUser.value.orgImg = res.data.orgImg
    }).then(() => {
        // 이미지 업로드 후 회원가입 코드 실행
        goSignup();
    })
};


// 회원가입
const goSignup = function () {


    if (signupUser.value.id === null || signupUser.value.id === '') {
        alert('아이디를 입력해주시기 바랍니다.')
        return;
    }

    // some() : 특정 조건을 만족하는 요소가 있는지 검사하는 코드
    if (users.value.some(user => user.id === id.value)) {
        alert('이미 존재하는 아이디입니다.');
        return;
    }

    // \W : 공백을 포함한 특수문자
    // /\W/ : 시작기호, 특수문자, 끝기호
    // some() 메서드는 배열 안의 어떤 요소라도 주어진 판별 함수를 적어도 하나라도 통과하는지 테스트함
    if (signupUser.value.password.length < 8 || signupUser.value.password.length > 20 || !/\W/.test(signupUser.value.password)) {
        alert("비밀번호는 8자리 이상 20자리 이하이며, 특수문자를 포함해야 합니다.")
        return;
    }

    if (signupUser.value.password !== password2.value) {
        alert('비밀번호가 일치하지 않습니다.');
        return;
    }

    if (signupUser.value.nickname === null || signupUser.value.nickname === '') {
        alert('닉네임을 입력해주시기 바랍니다.')
        return;
    }

    if (users.value.some(user => user.nickname === nickname.value)) {
        alert('이미 존재하는 닉네임입니다.');
        return;
    }

    if (signupUser.value.name === null || signupUser.value.name === '') {
        alert('이름을 입력해주시기 바랍니다.')
        return;
    }

    if (signupUser.value.email === null || signupUser.value.email === '') {
        alert('이메일을 입력해주시기 바랍니다.')
        return;
    }

    if (!signupUser.value.email.includes('@')) {
        alert("이메일에 @가 포함되어야 합니다.")
        return;
    }

    if (users.value.some(user => user.email == email.value)) {
        alert('이미 존재하는 이메일입니다.');
        return;
    }

    if (signupUser.value.targetWeight === null || signupUser.value.targetWeight === '' || signupUser.value.targetWeight <= 0) {
        alert('목표 체중을 입력해주시기 바랍니다.')
        return;
    }

    if (signupUser.value.targetTime === null || signupUser.value.targetTime === '' || signupUser.value.targetTime <= 0) {
        alert('하루 목표 운동 시간을 입력해주시기 바랍니다.')
        return;
    }



    axios({
        url: `http://localhost:8080/updown/user/signup`,
        method: 'POST',
        data: signupUser.value,
        headers: {
            "Content-Type": "application/json"
        },
    })
        .then(() => {
            router.push({ name: 'login' })
        })
}

getUserList();
</script>

<style scoped>
.title {
    font-size: 2.5rem;
    font-weight: 600;
    margin-top: 0.5rem;
    margin-left: 7.5rem;
}
.signup {
    display: flex;
    flex-direction: column;
    width: 500px;
    overflow: auto;
    height: 600px;
    line-height: 33px;
}

.signup::-webkit-scrollbar{
    width: 10px; 
}
.signup::-webkit-scrollbar-thumb { 
    background-color: #666; 
    border-radius: 10px;
}
.signup::-webkit-scrollbar-track { 
    background-color: #dcdcdc;  
    border-radius: 10px;
    box-shadow: inset 0px 0px 5px white;
}
.signup>:not(caption)>*>* {
    border-bottom-width: var(--bs-border-width);
    border-bottom-color: white;
}

#signup{
    text-align: right;
    width: 400px;
}
h2 {
    text-align: center;
}
label{
    font-size: 1rem;
    font-weight: 600;
    margin-bottom: 0.5rem;
}
input {
    border: 1px solid #929191;
    border-radius: 7.5px;
    width: 400px;
}

#gosignup {
    border: 1px solid #a8a7a7;
    border-radius: 7.5px;
    height: 40px;
    background-color: #a9a8a8;
    color: white;
}

.radio-container {
    display: flex;
    justify-content: space-between;
    width: 300px;

}

.radio {
    display: flex;
    vertical-align: middle;
}

.signup-container {
    padding-top: 50px;
    padding-bottom: 50px;
    width: 740px;
}

a {
    width: 400px;
    text-align: right;
    text-decoration: underline;
    color: #a8a7a7;
}

#gologin{
    text-align: right;
    
}

h2{
     width: 400px;
}
</style>