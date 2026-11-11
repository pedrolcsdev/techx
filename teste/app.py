from flask import Flask, jsonify

app = Flask(__name__)

TASKS = [
    {"id": 1, "title": "Estudar Docker"},
    {"id": 2, "title": "Estudar o pipeline"}
]

@app.get("/")
def index():
    return jsonify(TASKS)

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=5000)