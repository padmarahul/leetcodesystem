import  axios from "axios";
import https from 'https';

const baseUrl = 'https://localhost:8443/lcms';
console.log(baseUrl)



const axiosInstance =axios.create({
    baseURL:baseUrl,
    httpsAgent: new https.Agent({
        rejectUnauthorized: false  // Ignore self-signed certificate in development
    }),
    headers: {
        'Content-Type': 'application/json',  // Ensure content type is set correctly
    },
    timeout: 5000,  // Optional: Set a timeout for requests (in ms)
})


export default axiosInstance;