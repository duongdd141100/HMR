export const Services = {
    get: async function(url, onSuccess, onFail) {
        await fetch(url,
            {
                method: 'GET',
                headers: {"Authorization": localStorage.getItem("token") || ''}
            }).then(response => response.json())
            .then(data => {
                if (data.code === '200') {
                    onSuccess(data);
                } else {
                    localStorage.setItem("token", "")
                    onFail(data);
                }
                return data;
            })
            .catch(error => {
                alert(error.message);
            })
    },
    post: async function(url, body, onSuccess, onFail) {
        await fetch(url,
            {
                method: 'POST',
                headers: {"Authorization": localStorage.getItem("token") || ''},
                body: body
            }).then(response => response.json())
            .then(data => {
                if (data.code === '200') {
                    onSuccess(data);
                } else {
                    localStorage.setItem("token", "")
                    onFail(data);
                }
            }).catch(error => {
                alert(error.message);
            })

    }
}