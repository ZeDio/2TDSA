import { StyleSheet } from "react-native";

export const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: "#f8f9fa",
    padding: 20,
  },
  form: {
    flexDirection: "row",
    marginBottom: 10,
  },
  // TextInput Style
  input: {
    flex: 1,
    backgroundColor: "#fff",
    borderRadius: 12,
    paddingHorizontal: 32,
    paddingVertical: 14,
    fontSize: 16,
    borderWidth: 1,
    borderColor: "#e9ecef",
    shadowColor: "#000",
    shadowOffset: { width: 0, height: 1 },
    shadowOpacity: 0.05,
    shadowRadius: 2,
    elevation: 2,
    marginRight: 12,
  },

  // Add Button
  button: {
    backgroundColor: "#6366f1",
    borderRadius: 12,
    paddingHorizontal: 15,
    justifyContent: "center",
    alignItems: "center",
    shadowColor: "#6366f1",
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.2,
    shadowRadius: 4,
    elevation: 3,
  },
  buttonText: {
    color: "#fff",
    fontSize: 32,
    fontWeight: "600",
  },

  // Todo List Items
  todoItem: {
    backgroundColor: "#fff",
    borderRadius: 12,
    padding: 16,
    marginBottom: 12,
    flexDirection: "row",
    alignItems: "center",
    justifyContent: "space-between",
    borderWidth: 1,
    borderColor: "#e9ecef",
  },
  todoText: {
    fontSize: 16,
    color: "#1e293b",
    flex: 1,
  },
});
