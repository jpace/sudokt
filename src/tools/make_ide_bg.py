# /// script
# dependencies = [
#   "pillow",
# ]
# ///

import os
import sys
import argparse
from PIL import Image, ImageDraw, ImageFont

def get_current_project_name():
    # Automatically extracts the name of the folder you are running the script from
    return os.path.basename(os.getcwd())

def parse_color_string(color_str):
    # Standardize input strings by stripping # or spaces
    color_str = color_str.lstrip('#').strip()
    try:
        if ',' in color_str:
            rgb = tuple(int(c.strip()) for c in color_str.split(','))
            # Convert RGB to a clean hex string for the filename formatting
            hex_str = "{:02x}{:02x}{:02x}".format(*rgb)
            return rgb, hex_str
        if len(color_str) == 6:
            rgb = tuple(int(color_str[i:i+2], 16) for i in (0, 2, 4))
            return rgb, color_str.lower()
        raise ValueError
    except Exception:
        print(f"Error parsing color choice '{color_str}'. Falling back to safe dark grey.")
        return (43, 45, 48), "2b2d30" # Default safe dark grey reference

def generate_background(width, height, bg_rgb, text, is_tiled, filename):
    # 1. Initialize canvas with the requested background color
    img = Image.new('RGB', (width, height), color=bg_rgb)

    # 2. Build a transparent text layer to support a clean, crisp rotation angle
    text_layer = Image.new('RGBA', (width, height), color=(0, 0, 0, 0))
    draw = ImageDraw.Draw(text_layer)

    # Scale down font size if building a small repeating tile
    font_size = 28 if is_tiled else 84
    try:
        font = ImageFont.truetype("DejaVuSans-Bold.ttf", font_size)
    except IOError:
        font = ImageFont.load_default()

    # Subtle white overlay text with a very low transparency index
    text_color = (255, 255, 255, 12 if is_tiled else 18)

    # 3. Calculate text bounding parameters to enforce exact centering
    bbox = draw.textbbox((0, 0), text, font=font)
    text_width = bbox[2] - bbox[0]
    text_height = bbox[3] - bbox[1]
    position = ((width - text_width) // 2, (height - text_height) // 2)

    # 4. Stamp text and angle it 30 degrees
    draw.text(position, text, fill=text_color, font=font)
    rotated_text = text_layer.rotate(30, resample=Image.BICUBIC)

    # 5. Composite back onto the base background color layer and save
    img.paste(rotated_text, (0, 0), rotated_text)
    img.save(filename)
    print(f"[{text}] Generated {width}x{height} image with color {bg_rgb} saved as: {filename}")

if __name__ == "__main__":
    parser = argparse.ArgumentParser(description="Automate custom project-specific IDE backgrounds.")
    parser.add_argument('--dest', default=".", help="Destination directory")
    parser.add_argument('--color', default="342a2a", help="Hex '342a2a' or RGB values 'R,G,B'")
    parser.add_argument('--mode', choices=['tiled', 'full'], default='tiled', help="Choose image scaling layout style")
    args = parser.parse_args()

    project_title = get_current_project_name()
    bg_color, color_name = parse_color_string(args.color)
    dir = args.dest

    # Build your exact custom filename formatting block
    output_filename = f"{args.dest}/bg_{color_name}_{args.mode}.png"

    if args.mode == 'tiled':
        generate_background(300, 300, bg_color, project_title, True, output_filename)
    else:
        generate_background(1920, 1080, bg_color, project_title, False, output_filename)