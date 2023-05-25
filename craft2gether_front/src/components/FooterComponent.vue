<template>
    <div class="footer">
        <div class="credits">
            <a v-bind="githubLien"><i class="fab fa-github"></i></a>
            <p>Created and Developped by : Ines HAMIDI</p>
        </div>
    </div>
</template>
<script>
import { ref } from 'vue'
export default {
    name: 'FooterComponent',

    data() {
        return {
            githubLien: ref({ href: "https://github.com/InesHAMIDI/ProjetFilRouge_2iTech_Craft2Gether", target: "_blank" }),
        }
    },
}
</script>
<style scoped>
.footer {
    position: fixed;
    height: auto;
    width: 100%;
    bottom: 0;
}
.credits{
    margin-top: 10px;
    margin-bottom: 10px;
    align-items: center;
    flex-direction: column;
    text-align: center;
}


</style>