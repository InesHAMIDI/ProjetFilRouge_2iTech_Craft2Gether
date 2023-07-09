import { defineStore } from 'pinia';

export const useConnection = defineStore('connection', {
    state() {
        return {
            userCredentials: "",
            userConnected: false
        }
    },
    
    getters: {
        getCredentials(state){
            return state.userCredentials
        },
        isConnected(state){
            return state.userConnected;
        }
    },
    
    actions: {
        login(data) {
            this.userConnected = true;
            this.userCredentials = {
                username: data.username,
                password: data.password
            }
            localStorage.setItem('credentials', JSON.stringify(this.userCredentials));
            console.log(JSON.parse(localStorage.getItem('credentials')))

        },

        logout() {
            this.userConnected = false;
            if(localStorage.getItem("credentials") != undefined) {
                    localStorage.removeItem('credentials');
            }
        },

    }
})
