<template>
    <div>
        <ListeComponent v-for="elt in listes" :key="elt.nom" :liste="elt"></ListeComponent>
    </div>
</template>
<script>
export default {
    name:"UserComponent",
    data(){
        listes: [
            //placeholders en attendant les vrais résultats
            {titre:"LISTE1", recettes:[ { }, { } ], collaborateurs: [ {} ],  createur: this },
            {titre:"liste2", recettes:[ { }, { } ], collaborateurs: [ {} ],  createur: this }
        ]
    }
}
</script>
<style scoped>
    
</style>