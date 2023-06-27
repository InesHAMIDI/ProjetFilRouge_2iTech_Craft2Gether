<template>
    <h1>Authentification</h1>
    <form @submit.prevent="seConnecter">
        <div>
            <label for="username">Nom d'utilisateur</label>
            <input type="text" v-model="user.username" id="username">
        </div>
        <div>
            <label for="password">Mot de passe</label>
            <input type="password" v-model="user.password" id="password">
        </div>
        <button>Se connecter</button>
    </form>
</template>

<script setup>
import axios from 'axios'
import { reactive } from 'vue'
import { useRouter } from 'vue-router'
const router = useRouter();
const user = reactive({ username: '', password: ''})

const seConnecter = () => {
    axios.post('localhost:3000/ctg/auth', {}, {
      auth: {
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