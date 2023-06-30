<template>
    <div class="form">
        <h1>Authentification</h1>
        <form @submit.prevent="seConnecter">
            <div class="us">
                <label for="username">Identifiant </label>
                <input type="text" v-model="user.username" id="username">
            </div>
            <div class="pass">
                <label for="password">Mot de passe </label>
                <input type="password" v-model="user.password" id="password">
            </div>
            <button> Se connecter </button>
        </form>
    </div>
</template> 

<script setup>
import axios from 'axios'
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter();
const user = reactive({ username: '', password: ''})

const seConnecter = () => {
    axios({
        method: 'post',
        url:'http://localhost:3000/ctg/auth',
        auth:{
            username: user.username,
            password: user.password
        }
    })
    .then(function() {
      router.push('home')
    })
    .catch(function(error) {
        console.log(error);
    });
}
</script>

<style scoped>
.form{
  display: flex;
  align-items: center;
  text-align: center;
  flex-direction: column;
}

.form .us, .form .pass{
    display: flex;
    flex-direction: row;
    justify-content: space-between;
}

label{
    margin-right: 15px;
}

button{
    margin-top: 10px;
    background-color: #717171;
    color: white;
    border-color: #2c3e50;
    border-radius: 6px;
    border: solid;
}

button:hover{
    background-color: #32465a;
}
</style>
