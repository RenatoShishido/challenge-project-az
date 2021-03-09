import AXIOS_INSTANCE from "./config";

class UtensilioService {
  static async listarUtensilios() {
    try {
      const response = await AXIOS_INSTANCE.get("/api/utensilio");

      return response.data;
    } catch (err) {
      throw err.response.data;
    }
  }
  static async listarUtensilioId(id) {
    try {
      const response = await AXIOS_INSTANCE.get(`/api/utensilio/${id}`);

      return response.data;
    } catch (err) {
      throw err.response.data;
    }
  }
  static async criarUtensilio(content) {
    try {
      const response = await AXIOS_INSTANCE.post(`/api/utensilio`, content);

      return response.data;
    } catch (err) {
      throw err.response.data;
    }
  }
  static async updateUtensilio(id, content) {
    try {
      const response = await AXIOS_INSTANCE.put(`/api/utensilio/${id}`, content);

      return response.data;
    } catch (err) {
      throw err.response.data;
    }
  }
  static async removeUtensilio(id) {
    try {
      const response = await AXIOS_INSTANCE.delete(`/api/utensilio/${id}`);

      return response.data;
    } catch (err) {
      throw err.response.data;
    }
  }
}


export default UtensilioService;