import axios from "axios";

export const getWithAuth = async (url, params = {}) => {
  return await axios.get(url, {
    params,
    headers: {
      "Content-Type": "application/json",
      Authorization: null,
    },
  });
};
export const getWithoutAuth = async (url, params = {}) => {
  return await axios.get(url, params);
};
export const postWithAuth = async (url, body) => {
  return await axios.post(url, body, {
    headers: {
      "Content-Type": "application/json",
      Authorization: null,
    },
  });
};
export const postWithoutAuth = async (url, body) => {
  return await axios.post(url, body, {
    headers: { "Content-Type": "application/json" },
  });
};
export const postWithoutAuthAsMultipart = async (url, body) => {
  return await axios.post(url, body, {
    headers: {
      "Content-Type": "multipart/form-data",
    },
  });
};
