import axiosInstance from "../axiosInstance";


 const LEETCODE_BASE_URL = 'https://localhost:8443/lcms';


class CustomerServices {

    updatePersonalDetails(userId, customer) {
        return axiosInstance.put(`${LEETCODE_BASE_URL}/customer/update/${userId}`, customer)
    }



}

export default new CustomerServices();




