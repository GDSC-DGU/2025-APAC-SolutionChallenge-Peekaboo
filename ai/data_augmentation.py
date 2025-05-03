import os
import cv2
import numpy as np
import uuid
from tensorflow.keras.preprocessing.image import ImageDataGenerator

data_dir = "data_org"
output_dir = "data_aug"
augmentation_factor = 5
target_size = (224, 224)

# 안전한 증강 옵션 (피부병용)
datagen = ImageDataGenerator(
    rotation_range=15,
    width_shift_range=0.05,
    height_shift_range=0.05,
    zoom_range=0.1,
    horizontal_flip=True,
    fill_mode='nearest'
)

for category in os.listdir(data_dir):
    category_dir = os.path.join(data_dir, category)
    output_category_dir = os.path.join(output_dir, category)

    os.makedirs(output_category_dir, exist_ok=True)

    for image_file in os.listdir(category_dir):
        image_path = os.path.join(category_dir, image_file)

        image = cv2.imread(image_path)
        if image is None:
            print(f"이미지 로딩 실패: {image_path}")
            continue

        image = cv2.cvtColor(image, cv2.COLOR_BGR2RGB)
        image = cv2.resize(image, target_size)
        image_arr = np.expand_dims(image, axis=0)

        aug_iter = datagen.flow(image_arr, batch_size=1)

        for i in range(augmentation_factor):
            aug_image = next(aug_iter)[0].astype(np.uint8)
            save_img = cv2.cvtColor(aug_image, cv2.COLOR_RGB2BGR)

            unique_name = f"{os.path.splitext(image_file)[0]}-{uuid.uuid4().hex[:6]}.jpg"
            output_path = os.path.join(output_category_dir, unique_name)

            cv2.imwrite(output_path, save_img)
