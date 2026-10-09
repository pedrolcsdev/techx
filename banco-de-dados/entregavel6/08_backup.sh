#!/usr/bin/env bash
set -Eeuo pipefail

SCRIPT_DIR="$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)"
DB_NAME="${DB_NAME:-}"
BACKUP_DIR="${BACKUP_DIR:-${SCRIPT_DIR}/backups}"

if [[ -z "$DB_NAME" ]]; then
    echo "Erro: defina DB_NAME com o nome do banco PostgreSQL." >&2
    exit 2
fi

if [[ ! "$DB_NAME" =~ ^[a-zA-Z0-9_-]+$ ]]; then
    echo "Erro: DB_NAME deve conter apenas letras, números, '_' ou '-'." >&2
    exit 2
fi

if ! command -v pg_dump >/dev/null 2>&1; then
    echo "Erro: pg_dump não foi encontrado no PATH." >&2
    exit 2
fi

mkdir -p -- "$BACKUP_DIR"
timestamp="$(date '+%Y%m%d_%H%M%S')"
backup_file="${BACKUP_DIR}/postgres_${DB_NAME}_${timestamp}.dump"
temporary_file="${backup_file}.tmp"
trap 'rm -f -- "$temporary_file"' EXIT

echo "Iniciando backup de ${DB_NAME}: ${backup_file}"
pg_dump -Fc --file="$temporary_file" --dbname="$DB_NAME"
mv -- "$temporary_file" "$backup_file"

find "$BACKUP_DIR" -maxdepth 1 -type f \
    -name "postgres_${DB_NAME}_*.dump" -mmin +43200 -delete
echo "Backup concluído. Backups com mais de 30 dias foram removidos."
