# 🎮 Soul Dev

> A 2D top-down action game built with **Java 17**, **JavaFX**, and **FXGL**.

---

## 📖 Giới thiệu

**Soul Dev** là một game hành động nhìn từ trên xuống (top-down) được phát triển bằng Java thuần với thư viện JavaFX và game engine FXGL. Người chơi điều khiển nhân vật di chuyển trên bản đồ tile-based, chiến đấu với các loại kẻ thù khác nhau bằng nhiều loại vũ khí.

---

## ✨ Tính năng

- 🗺️ **Bản đồ tile-based** — Bản đồ được xây dựng từ tileset, hỗ trợ va chạm với tường và vật cản
- 🧍 **Nhân vật có thanh máu** — Người chơi có HP được hiển thị trực tiếp trên màn hình
- ⚔️ **Hệ thống vũ khí đa dạng**:
  - **Kiếm (Sword)** — Tấn công cận chiến bằng click chuột, có cooldown
  - **Cung (Bow) + Mũi tên (Arrow)** — Tấn công tầm xa theo hướng chuột
  - **Súng (Gun)** — Tài nguyên sẵn sàng để mở rộng
- 👾 **Nhiều loại kẻ thù**:
  - **Mob1** — Kẻ thù cận chiến, có chế độ tấn công nhanh (crit mode), tốc độ tăng khi vào trạng thái tấn công
  - **Mob2** — Kẻ thù tầm xa, có thể bắn đạn
  - **Ghost** — Loại kẻ thù đặc biệt
- 🧠 **AI kẻ thù** — Di chuyển theo hướng player khi trong bán kính phát hiện, đổi sang chế độ di chuyển ngẫu nhiên khi không tấn công
- 💥 **Hệ thống va chạm** — Xử lý va chạm giữa player–enemy, enemy–map, và projectile–enemy
- 🖱️ **Con trỏ tùy chỉnh** — Custom cursor được render bằng ảnh PNG

---

## 🛠️ Công nghệ sử dụng

| Thành phần        | Phiên bản |
|-------------------|-----------|
| Java              | 17        |
| JavaFX            | 17.0.6    |
| FXGL (Game Engine)| 17.3      |
| JavaFX Media      | 21.0.2    |
| ControlsFX        | 11.2.1    |
| Build Tool        | Maven     |
| Testing           | JUnit 5.10.2 |

---

## 📁 Cấu trúc dự án

```
Soul Dev/
├── src/main/java/com/game/Soul Dev/
│   ├── controller/
│   │   ├── CanvasController.java     # Controller chính của canvas game
│   │   └── ClientController.java     # Controller client
│   ├── entity/
│   │   ├── EntityInterface.java      # Interface chung cho các entity
│   │   ├── EntityMove.java           # Logic di chuyển entity
│   │   ├── EntityRender.java         # Logic render entity
│   │   ├── player/
│   │   │   ├── Player.java           # Nhân vật chính
│   │   │   └── HealthBar.java        # Thanh máu của player
│   │   └── enemy/
│   │       ├── Enemy.java            # Base class cho kẻ thù
│   │       ├── EnemyMove.java        # Logic di chuyển kẻ thù
│   │       ├── EnemyRender.java      # Render sprite kẻ thù
│   │       ├── Mob1.java             # Kẻ thù cận chiến
│   │       ├── Mob2.java             # Kẻ thù tầm xa
│   │       └── Ghost.java            # Kẻ thù đặc biệt
│   ├── eventHandle/
│   │   ├── KeyHandle.java            # Xử lý input bàn phím
│   │   ├── EnemyHandle.java          # Xử lý AI và logic kẻ thù
│   │   ├── CollisionHandle.java      # Xử lý va chạm
│   │   └── Skill.java                # Hệ thống kỹ năng / attack pattern
│   ├── map/
│   │   ├── Map.java                  # Interface bản đồ
│   │   ├── MapMatrix.java            # Ma trận tile của bản đồ
│   │   ├── MapImage.java             # Render ảnh bản đồ
│   │   ├── MapRender.java            # Xử lý render bản đồ
│   │   └── MapMove.java              # Camera / cuộn bản đồ
│   ├── weapon/
│   │   ├── Weapon.java               # Interface vũ khí
│   │   ├── sword/
│   │   │   ├── Sword.java
│   │   │   ├── SwordAttack.java      # Logic tấn công kiếm
│   │   │   └── SwordRender.java
│   │   ├── bow/
│   │   │   ├── Bow.java
│   │   │   ├── BowAttack.java        # Logic tấn công cung
│   │   │   └── BowRender.java
│   │   └── arrow/
│   │       ├── Arrow.java
│   │       └── ArrowRender.java
│   └── gameStart/
│       ├── main.java                 # Entry point khởi động app
│       └── runGame.java              # Khởi tạo scene và game loop
└── src/main/resources/
    ├── images/
    │   ├── player/                   # Sprite sheet nhân vật
    │   ├── mob/                      # Sprite sheet kẻ thù
    │   ├── weapon/                   # Ảnh vũ khí
    │   ├── map/
    │   │   ├── Map/                  # Tile images (0.png → 23.png)
    │   │   └── Tilesets/             # Tileset đầy đủ
    │   └── cursorImage/              # Ảnh con trỏ tùy chỉnh
    └── com/game/Soul Dev/
        ├── canvas.fxml               # Layout canvas game
        └── client.fxml               # Layout client
```

---

## 🎮 Điều khiển

| Phím / Nút        | Hành động              |
|-------------------|------------------------|
| `W`               | Di chuyển lên          |
| `S`               | Di chuyển xuống        |
| `A`               | Di chuyển sang trái    |
| `D`               | Di chuyển sang phải    |
| `R`               | Đổi vũ khí (Kiếm ↔ Cung) |
| `Click chuột trái`| Tấn công               |
| `Space`           | Kỹ năng đặc biệt       |
| `Enter`           | Xác nhận               |

> Vũ khí **nhắm theo hướng chuột** — Xoay chuột để điều hướng tấn công.

---

## ⚙️ Yêu cầu hệ thống

- **JDK 17** trở lên
- **Maven 3.8+**
- Hệ điều hành: Windows / macOS / Linux

---

## 🚀 Cài đặt & Chạy game

### 1. Clone repository

```bash
git clone https://github.com/lqdung24/Soul-Dev.git
cd Soul-Dev
```

### 2. Build project

```bash
./mvnw clean install
```

### 3. Chạy game

```bash
./mvnw javafx:run
```

> **Windows**: Dùng `mvnw.cmd` thay vì `./mvnw`
>
> ```cmd
> mvnw.cmd javafx:run
> ```

---

## 🏗️ Kiến trúc

Game được xây dựng theo mô hình **Game Loop** với các thành phần tách biệt:

```
main.java ──► runGame.java ──► CanvasController.java
                                       │
                    ┌──────────────────┼──────────────────┐
                    ▼                  ▼                   ▼
               MapRender           Player              EnemyHandle
               MapMove             KeyHandle           CollisionHandle
               MapMatrix           HealthBar           Skill (AI Pattern)
                    │                  │                   │
                    └──────────────────┴───────────────────┘
                                       │
                                  Weapon System
                              (Sword / Bow / Arrow)
```

- **Entity System**: Mỗi entity kế thừa `EntityRender`, có `update(deltaTime)` riêng
- **Event Handles**: Tách biệt logic xử lý input, AI, và va chạm khỏi entity
- **Camera System**: `MapMove` xử lý offset và cuộn bản đồ theo vị trí player

---

## 👥 Contributors

| GitHub | Vai trò |
|--------|---------|
| [@lqdung24](https://github.com/lqdung24) | Developer |

---

## Demo
[![Watch video](https://img.youtube.com/vi/0UcOYpdRKNI/maxresdefault.jpg)](https://www.youtube.com/watch?v=0UcOYpdRKNI)

---

<p align="center">
  Made with ❤️ using Java + JavaFX + FXGL
</p>
