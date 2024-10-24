
import axiosInstance from "../axiosInstance";

const LEETCODE_BASE_URL = 'https://localhost:8443/lcms';



class PaymentServices  {

    createPayment(userId, cart) {
        return axiosInstance.post(`${LEETCODE_BASE_URL}/payment/${userId}/create`,cart)
    }

    trackOrder(orderId) {
        return axiosInstance.get(`${LEETCODE_BASE_URL}/payment/trackorderdetails/${orderId}`)
    }

    manageOnlineSales() {
        return axiosInstance.get(`${LEETCODE_BASE_URL}/payment/getOnlineSales`)
    }

}

export default new PaymentServices();




