<template>
    <div class="card">
        <img class="card-img-top" v-bind="profilePic" alt="Card image cap">
    <div class="card-body">
        <h5 class="card-title">{{ user.name }}</h5>
        <p class="card-text">Some quick example text to build on the card title and make up the bulk of the card's content.</p>
        <a href="#" class="btn btn-primary">Go somewhere</a>
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

        }
    },
    computed:{
        profilePic() {
            const randomFile = require('select-random-file')
            const dir = '../public/Profile_pictures'
            randomFile(dir, (file) => {
                return file
            });
            return ""
        },

        nombredeListes(){
            return 0
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
    
</style>