<template>
    <div class="form">
        <h1>Authentification</h1>
        <form @submit="seConnecter">
            <div class="us">
                <label for="username">Identifiant </label>
                <input type="text" id="username">
            </div>
            <div class="pass">
                <label for="password">Mot de passe </label>
                <input type="password" id="password">
            </div>
            <button> Se connecter </button>
        </form>
    </div>
</template> 

<script>
import { useConnection } from '@/connexion/CheckConnection'
import axios from 'axios'
import { useRouter } from 'vue-router'
const router = useRouter();

	export default {
		components: {
		},
		data() {
			return {
				connection: useConnection()
			}
		},
		methods: {
            seConnecter(values){
                axios.post(`${this.baseUrl}/auth`, values)
                .then(res => {
                    this.connection.login(res.data);
                    console.log(res.data)
                    router.push('home');
                })
                .catch(function(error) {
                    console.log(error);
                });
            }
    }
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
