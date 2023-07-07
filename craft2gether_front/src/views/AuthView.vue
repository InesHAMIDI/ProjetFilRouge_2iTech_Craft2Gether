<template>
    <div class="form">
        <h1>Authentification</h1>
        <form @submit.prevent="seConnecter">
            <div class="us">
                <label for="username">Identifiant </label>
                <input type="text" v-model="username" id="username">
            </div>
            <div class="pass">
                <label for="password">Mot de passe </label>
                <input type="password" v-model="password" id="password">
            </div>
            <button>Se connecter</button>
        </form>
        <p>{{ erreur }}</p>
    </div>
</template> 

<script>
import { useConnection } from '@/connexion/CheckConnection'

	export default {
		components: {
		},
		data() {
			return {
				connection: useConnection(),
                password: "",
                username: "",
                erreur: "",
			}
		},
		methods: {
            seConnecter(){
                this.axios
                .post(`${this.baseUrl}/auth`, null, {
                    params:{
                        username: this.username, 
                        password: this.password
                    }
                })
                .then(() => {
                    this.connection.login({
                        username: this.username, 
                        password: this.password
                    });
                    this.$router.push({ name: 'home'})
                })
                .catch(error => {
                    console.log("bboofdbs")
                    this.erreur = error.data;
                });
            }
    }
}
</script>

<style scoped>
p{
    color:firebrick;
}
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
