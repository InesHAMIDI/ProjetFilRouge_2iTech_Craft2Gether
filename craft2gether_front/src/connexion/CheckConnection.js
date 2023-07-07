import { defineStore } from 'pinia';

export const useConnection = defineStore('connection', {
    state() {
        return {
            userCredentials: "",
            userRoles:"",
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
            this.userRoles = data.roles;
            localStorage.setItem('credentials', JSON.stringify(this.userCredentials));
            console.log(JSON.parse(localStorage.getItem('credentials')))

        },

        logout() {
            this.userConnected = false;
            if(localStorage.getItem("credentials") != undefined) {
                    localStorage.removeItem('credentials');
            }
        },

        lastConnectionAvailable() {
            let cred = localStorage.getItem("credentials");
            if(cred != undefined) {
                    this.userConnected = true;
                }
            }
        },
    }
)
