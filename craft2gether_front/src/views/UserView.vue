<template>
    <div class="user-card">
        <div class="card">
            <div class="card-body">
                <h5 class="card-title">{{ user.name }}</h5>
                <p class="card-text">Listes : {{ nombredeListes }}</p>
            </div>
        </div>
    </div>
</template>
<script>
export default {
    components:{
        
    },
    data(){
        return{
            user:{},
            profilePic: ""
        }
    },
    computed:{
        nombredeListes(){
            if(this.user.listes == undefined || this.user.listes == null)
                return 0;
            else
                return this.user.listes.lenght;
        }
    },
    mounted() {
        this.axios.get(`${this.baseUrl}/users/${this.id}`)
            .then(response => this.user = response.data)
            .catch(err => this.erreur = err);
    },
}

</script>
<style scoped>
    .user-card{
        align-items: center;
        border:2px;
    }
</style>