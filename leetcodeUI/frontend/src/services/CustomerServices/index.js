import axiosInstance from "../axiosInstance";


 const LEETCODE_BASE_URL = 'https://localhost:8443/lcms';


class CustomerServices {

    updatePersonalDetails(userId, customer) {
        return axiosInstance.put(`${LEETCODE_BASE_URL}/customer/update/${userId}`, customer)
    }

      createDiscussion(customerId, title, content){
        return axiosInstance.post(`${LEETCODE_BASE_URL}/discussions/creatediscussion`, null, {
          params: {
            customerId,
            title,
            content,
          },
        });
      };

      getAllDiscussions() {
        return axiosInstance.get(`${LEETCODE_BASE_URL}/discussions/getalldiscussions`)
    }

}

export default new CustomerServices();




