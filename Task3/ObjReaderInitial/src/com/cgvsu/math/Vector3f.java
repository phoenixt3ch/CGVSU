package com.cgvsu.math;

// Это заготовка для собственной библиотеки для работы с линейной алгеброй
public class Vector3f {
    public Vector3f(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public boolean equals(Vector3f other) {
        // todo: желательно, чтобы это была глобальная константа
        final float eps = 1e-7f;
        return Math.abs(x - other.x) < eps && Math.abs(y - other.y) < eps && Math.abs(z - other.z) < eps;
    }

    float x, y, z;

    public Vector3f add(Vector3f other) {
        this.x += other.x;
        this.y += other.y;
        this.z += other.z;
        return this;
    }

    public Vector3f subtract(Vector3f other) {
        return new Vector3f(this.x - other.x, this.y - other.y, this.z - other.z);
    }

    public Vector3f divide(float scalar) {
        this.x /= scalar;
        this.y /= scalar;
        this.z /= scalar;
        return this;
    }

    public Vector3f cross(Vector3f other) {
        float x = this.x;
        float y = this.y;
        float z = this.z;
        this.x = y * other.z - z * other.y;
        this.y = z * other.x - x * other.z;
        this.z = x * other.y - y * other.x;
        return this;
    }

    public Vector3f normalize() {
        float length = (float) Math.sqrt(x * x + y * y + z * z);
        this.x /= length;
        this.y /= length;
        this.z /= length;
        return this;
    }
}
