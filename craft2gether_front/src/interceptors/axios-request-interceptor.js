export function axiosInterceptor(config)
      {
        if (localStorage.getItem('token') != null && localStorage.getItem('token') != undefined) {
            const credentials = localStorage.getItem('token');
            config.headers.setAuthorization(`Basic ${credentials}`)  
        }
        return config
    }