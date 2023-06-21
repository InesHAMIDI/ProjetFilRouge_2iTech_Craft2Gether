export function axiosInterceptor(config)
      {
        if (localStorage.getItem('token') != null && localStorage.getItem('token') != undefined) {
            const token = localStorage.getItem('token');
            config.headers.setAuthorization(`Bearer ${token}`)  
        }
        return config
    }