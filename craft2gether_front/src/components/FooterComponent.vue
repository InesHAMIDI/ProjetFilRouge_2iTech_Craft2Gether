<template>
    <div class="footer">
        <a v-bind="githubLien"><i class="fab fa-github"></i></a>
        <p>Created and Developped by : Ines HAMIDI</p>
        <p>2023</p>
    </div>
</template>
<script>
import {ref} from 'vue'
export default {
    name: 'FooterComponent',

    data() {
        return {
            githubLien: ref({href:"https://github.com/InesHAMIDI/ProjetFilRouge_2iTech_Craft2Gether", target: "_blank"})
        }
    },
}
</script>
<style scoped>
    .footer{
        background-color: grey;
        position: fixed;
        height: auto;
        width: 100%;
        bottom:0;
        align-items: flex-start;
    }
    a{
        color:white;
        font-size: 32px;
    }
    p{
        color:lightblue;
    }

</style>