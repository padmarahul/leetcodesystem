import axiosInstance from "../axiosInstance";

const LEETCODE_BASE_URL = 'https://localhost:8443/lcms';



class LoginServices  {

    login(username, password) {
        return axiosInstance.post(`${LEETCODE_BASE_URL}/user/login/${username}/${password}`)
    }

    verifyOtp(id,otp)
    {
        return axiosInstance.post(`${LEETCODE_BASE_URL}/user/login/${id}/verifyotp/${otp}`)
    }

    signUp(user)
    {
        return axiosInstance.post(`${LEETCODE_BASE_URL}/user/signup`,user)
    }

    changepassword(userName, password){
   return axiosInstance.put(`${LEETCODE_BASE_URL}/user/changepassword/${userName}/${password}`)
    }



}

export default new LoginServices();




