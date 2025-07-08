# Vault - Thư viện trừu tượng cho Bukkit/Spigot

## Dành cho nhà phát triển:
Vui lòng xem trang [VaultAPI](https://www.github.com/MilkBowl/VaultAPI) để biết thông tin về phát triển với API của Vault. Trong quá khứ, bạn sẽ sử dụng cùng một artifact với máy chủ đã cài đặt, nhưng API hiện đã được tách khỏi dự án chính và nằm dưới một tên artifact khác. Hãy đảm bảo bạn điều chỉnh thay đổi này trong quy trình build của mình.

## Cài đặt
Cài đặt Vault đơn giản như sao chép file "Vault.jar" được cung cấp vào thư mục "<thư-mục-cài-đặt-bukkit>/plugins" của bạn, và phần còn lại là tự động! Nếu bạn muốn thực hiện thay đổi cấu hình, điều này có thể được thực hiện thông qua tệp cấu hình nhưng không cần thiết trong hầu hết các trường hợp.

## Tại sao nên dùng Vault?
Tôi không có sự ưu tiên thư viện nào phù hợp nhất với plugin và nỗ lực phát triển của bạn. Thực sự, tôi nghĩ một bộ giải pháp trung tâm (hay là... Vault) là con đường thích hợp hơn là tập trung vào một danh mục plugin duy nhất. Đó là nơi ý tưởng cho Vault ra đời.

Vậy, những tính năng nào tôi _nghĩ_ bạn sẽ thích nhất?

* Không cần phải bao gồm mã nguồn của tôi trong plugin của bạn
  Tất cả Vault được chạy trong plugin riêng của nó, vì vậy tất cả những gì bạn cần làm là lấy một instance của nó! Điều này đơn giản hóa các vấn đề với nhiều plugin sử dụng cùng một namespace. Chỉ cần đơn giản thêm Vault.jar vào file zip tải xuống của bạn!
* Phạm vi rộng của các plugin được hỗ trợ
  Tôi muốn một lớp trừu tượng không chỉ cho các plugin Kinh tế mà còn cho các plugin Quyền hạn.
* Lựa chọn!
  Đó là một nửa niềm vui của Bukkit! Chúng ta được chọn những gì để sử dụng. Nhiều lựa chọn hơn chưa bao giờ làm tổn thương các nhà phát triển, vì vậy hãy vì sự lựa chọn!

## Quyền hạn
* vault.admin
  - Xác định xem người chơi có nên nhận thông báo cập nhật hay không

## Giấy phép
Copyright (C) 2011-2025 Morgan Humes <morgan@lanaddict.com>

Vault là phần mềm miễn phí: bạn có thể phân phối lại và/hoặc sửa đổi nó theo các điều khoản của Giấy phép Công cộng GNU Lesser như được xuất bản bởi Quỹ Phần mềm Tự do, phiên bản 3 của Giấy phép, hoặc (tùy chọn của bạn) bất kỳ phiên bản nào sau này.

Vault được phân phối với hy vọng rằng nó sẽ hữu ích, nhưng KHÔNG CÓ BẤT KỲ BẢO HÀNH NÀO; thậm chí không có bảo đảm ngụ ý về KHẢ NĂNG BÁN ĐƯỢC hoặc PHÙ HỢP CHO MỘT MỤC ĐÍCH CỤ THỂ. Xem Giấy phép Công cộng GNU Lesser để biết thêm chi tiết.

Bạn nên đã nhận được một bản sao của Giấy phép Công cộng GNU Lesser cùng với Vault. Nếu không, hãy xem <http://www.gnu.org/licenses/>.
## Dành cho nhà phát triển:

Vui lòng xem trang [VaultAPI](https://www.github.com/MilkBowl/VaultAPI) để biết thông tin về việc phát triển với API của Vault. Trước đây, bạn sẽ sử dụng cùng một artifact như máy chủ đã cài đặt, nhưng API hiện đã được tách khỏi dự án chính và nằm dưới một artifact khác. Hãy đảm bảo bạn cập nhật thay đổi này trong quy trình build của mình.

## Cài đặt

Cài đặt Vault rất đơn giản, chỉ cần sao chép file "Vault.jar" vào thư mục "<bukkit-install-dir>/plugins", phần còn lại sẽ tự động! Nếu bạn muốn thay đổi cấu hình, có thể thực hiện qua file cấu hình nhưng thường không cần thiết. Xem phần "Cấu hình nâng cao" để biết thêm chi tiết.

## Tại sao nên dùng Vault?

Tôi không thiên vị thư viện nào phù hợp nhất cho plugin và quá trình phát triển của bạn. Thực sự, tôi nghĩ một bộ giải pháp trung tâm (Vault) là hướng đi đúng thay vì chỉ tập trung vào một loại plugin. Đó là lý do Vault ra đời.

Vậy, những tính năng nào tôi nghĩ bạn sẽ thích nhất?

- Không cần nhúng mã nguồn của tôi vào plugin của bạn  
  Tất cả Vault chạy trong plugin riêng, bạn chỉ cần lấy một instance của nó! Điều này giúp tránh xung đột namespace giữa các plugin. Chỉ cần thêm Vault.jar vào file zip tải về!
- Hỗ trợ nhiều plugin  
  Tôi muốn tạo một lớp trừu tượng không chỉ cho các plugin kinh tế mà còn cho cả plugin quyền hạn.
- Lựa chọn!  
  Đó là một phần thú vị của Bukkit! Chúng ta được quyền chọn cái mình muốn dùng. Càng nhiều lựa chọn càng tốt cho nhà phát triển.

## Quyền hạn

- vault.admin
  - Xác định người chơi có nhận được thông báo cập nhật hay không

## Giấy phép

Bản quyền (C) 2011-2018 Morgan Humes <morgan@lanaddict.com>

Vault là phần mềm miễn phí: bạn có thể phân phối lại và/hoặc sửa đổi theo các điều khoản của Giấy phép GNU Lesser General Public License do Free Software Foundation phát hành, phiên bản 3 hoặc (tùy chọn) bất kỳ phiên bản nào sau đó.

Vault được phát triển với hy vọng sẽ hữu ích, nhưng KHÔNG CÓ BẢO HÀNH; kể cả bảo đảm NGẦM ĐỊNH về TÍNH THƯƠNG MẠI hoặc PHÙ HỢP MỤC ĐÍCH CỤ THỂ. Xem Giấy phép GNU Lesser General Public License để biết thêm chi tiết.

Bạn nên nhận được một bản sao của GNU Lesser General Public License cùng với Vault. Nếu không, xem <http://www.gnu.org/licenses/>.

## Build

Vault đi kèm tất cả thư viện cần thiết để build từ nhánh hiện tại và có cả file build Apache Ant (build.xml) và Maven (pom.xml). Maven hiện là phương pháp build ưu tiên.

## Phụ thuộc

Vì Vault cung cấp cầu nối tới các plugin khác, nên cần các file nhị phân của chúng để build. Để thuận tiện, chúng đã được đưa vào thư mục lib và sẽ được cập nhật định kỳ. Đối với nhà phát triển plugin, không cần dùng các thư viện này khi tích hợp Vault, chỉ cần build với Vault là đủ.

## Các plugin được hỗ trợ

Vault cung cấp lớp trừu tượng cho các loại plugin và plugin sau. Nếu bạn có plugin riêng muốn được hỗ trợ, bạn cần tự thêm connector trong plugin của mình vì Vault không còn duy trì các connector mới.

- Quyền hạn (Permissions)

  - bPermissions
  - bPermissions 2 (https://dev.bukkit.org/projects/bpermissions)
  - DroxPerms
  - Group Manager (Essentials) (https://forums.bukkit.org/threads/15312/)
  - LuckPerms (https://www.spigotmc.org/resources/luckperms-an-advanced-permissions-plugin.28140/)
  - OverPermissions (https://dev.bukkit.org/projects/overpermissions)
  - Permissions 3 (https://forums.bukkit.org/threads/18430/)
  - PermissionsBukkit
  - Permissions Ex (PEX) (https://forums.bukkit.org/threads/18140/)
  - Privileges
  - rscPermissions
  - SimplyPerms
  - SuperPerms (mặc định của Bukkit)
  - TotalPermissions (https://dev.bukkit.org/projects/totalpermissions)
  - XPerms
  - zPermissions

- Chat
  - bPermissions
  - Group Manager (Essentials) (https://forums.bukkit.org/threads/15312/)
  - iChat
  - LuckPerms (https://www.spigotmc.org/resources/luckperms-an-advanced-permissions-plugin.28140/)
  - mChat
  - mChatSuite
  - OverPermissions (https://dev.bukkit.org/projects/overpermissions)
  - Permissions 3 (https://forums.bukkit.org/threads/18430/)
  - Permissions Ex (PEX) (https://forums.bukkit.org/threads/18140/)
  - rscPermissions
  - TotalPermissions (https://dev.bukkit.org/projects/totalpermissions)
  - zPermissions

## Tương thích Minecraft 1.21.4

> **Lưu ý:** Phiên bản Vault này có thể chưa hoàn toàn tương thích với Minecraft 1.21.4. Cần cập nhật mã nguồn để hỗ trợ API Bukkit/Spigot/Paper 1.21.4. Hãy đảm bảo bạn đang sử dụng bản Vault mới nhất hoặc cập nhật mã nguồn để tương thích với 1.21.4.
