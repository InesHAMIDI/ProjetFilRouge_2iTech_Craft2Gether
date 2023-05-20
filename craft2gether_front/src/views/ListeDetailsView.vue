<template>
    <div class="dropdown">
        <a class="btn btn-secondary dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
            {{ liste.titre }}
        </a>

        <table class="dropdown-menu table table-striped">
            <thead>
                <tr>
                    <th scope="col">Bloc</th>
                    <th scope="col">Status</th>
                    <th scope="col"></th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="elt in ingredients" :key="elt">
                    <td>{{ elt.name }}</td>
                    <td>{{ elt.status }}</td>
                    <td><router-link :to="{
                        name: 'liste-details',
                        params: { id: elt.id }
                    }"></router-link>
                    </td>
                </tr>
            </tbody>
        </table>
    </div>
</template>
<script>
export default {
    data() {
        return {
            liste: {},
            ingredients: []
        }
    },

    computed:{
        id(){
            return this.$route.params.id;
        }
    },

    mounted() {
        this.axios.get(`${this.baseUrl}/listes/${this.id}`)
            .then(response => this.liste = response.data)
            .catch(err => this.erreur = err);
    },

}
</script>
<style></style>