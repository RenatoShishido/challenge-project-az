import AXIOS_INSTANCE from "./config";

class ClienteService {
  static async listarClientes(size, page) {
    try {
      const response = await AXIOS_INSTANCE.get(`/api/administrador?size=${size}&page=${page}`);

      return response.data;
    } catch (err) {
      throw err.response.data;
    }
  }
  static async listarClienteId(id) {
    try {
      const response = await AXIOS_INSTANCE.get(`/api/administrador/${id}`);

      return response.data;
    } catch (err) {
      throw err.response.data;
    }
  }
  static async criarCliente(content) {
    try {
      const response = await AXIOS_INSTANCE.post(`/api/administrador`, content);

      return response.data;
    } catch (err) {
      throw err.response.data;
    }
  }
  static async updateCliente(id, content) {
    try {
      const response = await AXIOS_INSTANCE.put(`/api/administrador/${id}`, content);

      return response.data;
    } catch (err) {
      throw err.response.data;
    }
  }
  static async removeCliente(id) {
    try {
      const response = await AXIOS_INSTANCE.delete(`/api/administrador/${id}`);

      return response.data;
    } catch (err) {
      throw err.response.data;
    }
  }
}


export default ClienteService;